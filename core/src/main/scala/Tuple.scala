

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Tuple {
    private[lity] def apply(c: Context)(xs: List[c.Tree]): c.Tree = {
        import c.universe._

        if (xs.isEmpty) {
            q"()"
        } else {
            val tn = TermName(s"Tuple${xs.length}")
            q"scala.$tn(..$xs)"
        }
    }

    private[lity] def toList(c: Context)(tup: c.Tree): List[c.Tree] = {
        import c.universe._

        tup match {
            case q"()" => Nil
            case q"${x: String}" => _toList(c)(c.typecheck(c.parse(x)))
            case x => _toList(c)(x)
        }
    }

    private[lity] def normalize(c: Context)(tup: c.Tree): c.Tree = {
        import c.universe._

        tup match {
            case q"${_}($x).->[${_}]($y)" => q"($x, $y)"
            case _ => tup
        }
    }

    private def _toList(c: Context)(tup: c.Tree): List[c.Tree] = {
        import c.universe._

        normalize(c)(tup) match {
            case q"${_}(..$xs)" => xs
            case _ => CompileError.illegalArgument(c)(tup, "tuple")
        }
    }


    object range {
        def apply(n: Any, m: Any): Any = macro RangeImpl.apply
    }

    final class RangeImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(n: c.Tree, m: c.Tree): c.Tree = Tuple(c) {
            List.range(Extract.Int(c)(n), Extract.Int(c)(m)).map { i =>
                q"$i"
            }
        }
    }


    object empty {
        def apply(tup: Any): Unit = macro EmptyImpl.apply
    }

    final class EmptyImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(tup: c.Tree): c.Tree = {
            q"()"
        }
    }


    object equal {
        def apply(tup1: Any, tup2: Any): Any = macro EqualImpl.apply
    }

    final class EqualImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(tup1: c.Tree, tup2: c.Tree): c.Tree = {
            val xs1 = Tuple.toList(c)(tup1)
            val xs2 = Tuple.toList(c)(tup2)

            val b = xs1.corresponds(xs2) { (x1, x2) =>
                x1.equalsStructure(x2)
            }
            q"$b"
        }
    }


    object filter {
        def apply(tup: Any, f: Any): Any = macro FilterImpl.apply
    }

    final class FilterImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(tup: c.Tree, f: c.Tree): c.Tree = Tuple(c) {
            Tuple.toList(c)(tup).filter { x =>
                AsBoolean(c)(q"${Here(c)}.Apply($f, $x)")
            }
        }
    }


    object find {
        def apply(tup: Any, f: Any): Any = macro FindImpl.apply
    }

    final class FindImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(tup: c.Tree, f: c.Tree): c.Tree = {
            val y = Tuple.toList(c)(tup).find { x =>
                AsBoolean(c)(q"${Here(c)}.Apply($f, $x)")
            }
            q"$y"
        }
    }


    object flatten {
        def apply(tups: Any): Any = macro FlattenImpl.apply
    }

    final class FlattenImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(tups: c.Tree): c.Tree = Tuple(c) {
            Tuple.toList(c)(tups).map { tup =>
                Tuple.toList(c)(tup)
            }.flatten
        }
    }


    object foldLeft {
        def apply(tup: Any, z: Any, f: Any): Any = macro FoldLeftImpl.apply
    }

    final class FoldLeftImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(tup: c.Tree, z: c.Tree, f: c.Tree): c.Tree = {
            Tuple.toList(c)(tup).foldLeft(z) { (z, x) =>
                q"${Here(c)}.Apply($f, ($z, $x))"
            }
        }
    }


    object foldRight {
        def apply(tup: Any, z: Any, f: Any): Any = macro FoldRightImpl.apply
    }

    final class FoldRightImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(tup: c.Tree, z: c.Tree, f: c.Tree): c.Tree = {
            Tuple.toList(c)(tup).foldRight(z) { (x, z) =>
                q"${Here(c)}.Apply($f, ($x, $z))"
            }
        }
    }


    object get {
        def apply(tup: Any, n: Int): Any = macro GetImpl.apply
    }

    final class GetImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(tup: c.Tree, n: c.Tree): c.Tree = {
            val i = Extract.Int(c)(n)
            Tuple.toList(c)(tup)(i)
        }
    }


    object head {
        def apply(tup: Any): Any = macro HeadImpl.apply
    }

    final class HeadImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(tup: c.Tree): c.Tree = {
            Tuple.toList(c)(tup).head
        }
    }


    object tail {
        def apply(tup: Any): Any = macro TailImpl.apply
    }

    final class TailImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(tup: c.Tree): c.Tree = Tuple(c) {
            Tuple.toList(c)(tup).tail
        }
    }


    object isEmpty {
        def apply(tup: Any): Int = macro IsEmptyImpl.apply
    }

    final class IsEmptyImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(tup: c.Tree): c.Tree = {
            val b = Tuple.toList(c)(tup).isEmpty
            q"$b"
        }
    }


    object length {
        def apply(tup: Any): Any = macro LengthImpl.apply
    }

    final class LengthImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(tup: c.Tree): c.Tree = {
            val n = Tuple.toList(c)(tup).length
            q"$n"
        }
    }


    object map {
        def apply(tup: Any, f: Any): Any = macro MapImpl.apply
    }

    final class MapImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(tup: c.Tree, f: c.Tree): c.Tree = Tuple(c) {
            Tuple.toList(c)(tup).map { x =>
                q"${Here(c)}.Apply($f, $x)"
            }
        }
    }


    object append {
        def apply(tup1: Any, x: Any): Any = macro AppendImpl.apply
    }

    final class AppendImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(tup1: c.Tree, x: c.Tree): c.Tree = Tuple(c) {
            Tuple.toList(c)(tup1) :+ x
        }
    }


    object prepend {
        def apply(tup1: Any, x: Any): Any = macro PrependImpl.apply
    }

    final class PrependImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(tup1: c.Tree, x: c.Tree): c.Tree = Tuple(c) {
            x :: Tuple.toList(c)(tup1)
        }
    }


    object reduceLeft {
        def apply(tup: Any, f: Any): Any = macro ReduceLeftImpl.apply
    }

    final class ReduceLeftImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(tup: c.Tree, f: c.Tree): c.Tree = {
            Tuple.toList(c)(tup).reduceLeft { (z, x) =>
                q"${Here(c)}.Apply($f, ($z, $x))"
            }
        }
    }


    object reduceRight {
        def apply(tup: Any, f: Any): Any = macro ReduceRightImpl.apply
    }

    final class ReduceRightImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(tup: c.Tree, f: c.Tree): c.Tree = {
            Tuple.toList(c)(tup).reduceRight { (x, z) =>
                q"${Here(c)}.Apply($f, ($x, $z))"
            }
        }
    }


    object reverse {
        def apply(tup: Any): Any = macro ReverseImpl.apply
    }

    final class ReverseImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(tup: c.Tree): c.Tree = Tuple(c) {
            Tuple.toList(c)(tup).reverse
        }
    }


    object toList {
        def apply(tup: Any): Any = macro ToListImpl.apply
    }

    final class ToListImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(tup: c.Tree): c.Tree = {
            val xs = Tuple.toList(c)(tup)
            q"scala.List(..$xs)"
        }
    }


    object transpose {
        def apply(tups: Any): Any = macro TransposeImpl.apply
    }

    final class TransposeImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(tups: c.Tree): c.Tree = Tuple(c) {
            Tuple.toList(c)(tups).map { tup =>
                Tuple.toList(c)(tup)
            }.transpose.map { xs =>
                q"(..$xs)"
            }
        }
    }


    object updated {
        def apply(tup: Any, n: Int, x: Any): Any = macro UpdatedImpl.apply
    }

    final class UpdatedImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(tup: c.Tree, n: c.Tree, x: c.Tree): c.Tree = Tuple(c) {
            val i = Extract.Int(c)(n)
            Tuple.toList(c)(tup).updated(i, x)
        }
    }
}
