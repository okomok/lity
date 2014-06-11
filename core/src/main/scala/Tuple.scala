

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Tuple {

    def treeFromList(c: Context)(xs: List[c.Tree]): c.Tree = {
        import c.universe._

        if (xs.isEmpty) {
            q"()"
        } else {
            val tn = TermName(s"Tuple${xs.length}")
            q"scala.$tn(..$xs)"
        }
    }


    def treeToList(c: Context)(tup: c.Tree): List[c.Tree] = {
        import c.universe._

        tup match {
            case q"${x: String}" => _treeToList(c)(c.typecheck(c.parse(x)))
            case x => _treeToList(c)(x)
        }
    }

    private def _treeToList(c: Context)(tup: c.Tree): List[c.Tree] = {
        import c.universe._

        treeDealias(c)(tup) match {
            case q"()" => Nil
            case q"${_}(..$xs)" => xs
            case _ => TypeError(c)("illegal argument", tup, "tuple")
        }
    }


    def treeDealias(c: Context)(tup: c.Tree): c.Tree = {
        import c.universe._

        tup match {
            case q"${_}($x).->[${_}]($y)" => q"($x, $y)"
            case _ => tup
        }
    }


    def apply(xs: Any*): Any = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        def apply(xs: c.Tree*): c.Tree = treeFromList(c)(xs.toList)
    }


    object range {
        def apply(n: Int, m: Int): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(n: c.Tree, m: c.Tree): c.Tree = treeFromList(c) {
                List.range(ExtractInt(c)(n), ExtractInt(c)(m)).map { i =>
                    q"$i"
                }
            }
        }
    }


    object empty {
        def apply(tup: Any): Unit = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree): c.Tree = {
                q"()"
            }
        }
    }


    object lequals {
        def apply(tup1: Any, tup2: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup1: c.Tree, tup2: c.Tree): c.Tree = {
                val xs1 = treeToList(c)(tup1)
                val xs2 = treeToList(c)(tup2)

                val b = xs1.corresponds(xs2) { (x1, x2) =>
                    x1.equalsStructure(x2)
                }
                q"$b"
            }
        }
    }


    object filter {
        def apply(tup: Any, f: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree, f: c.Tree): c.Tree = treeFromList(c) {
                treeToList(c)(tup).filter { x =>
                    AsBoolean(c)(q"${Here(c)}.apply($f, $x)")
                }
            }
        }
    }


    object find {
        def apply(tup: Any, f: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree, f: c.Tree): c.Tree = {
                val y = treeToList(c)(tup).find { x =>
                    AsBoolean(c)(q"${Here(c)}.apply($f, $x)")
                }
                q"$y"
            }
        }
    }


    object flatten {
        def apply(tups: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tups: c.Tree): c.Tree = treeFromList(c) {
                treeToList(c)(tups).map { tup =>
                    treeToList(c)(tup)
                }.flatten
            }
        }
    }


    object foldLeft {
        def apply(tup: Any, z: Any, f: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree, z: c.Tree, f: c.Tree): c.Tree = {
                treeToList(c)(tup).foldLeft(z) { (z, x) =>
                    q"${Here(c)}.apply($f, ($z, $x))"
                }
            }
        }
    }


    object foldRight {
        def apply(tup: Any, z: Any, f: Any): Any = macro Impl.apply

         final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree, z: c.Tree, f: c.Tree): c.Tree = {
                treeToList(c)(tup).foldRight(z) { (x, z) =>
                    q"${Here(c)}.apply($f, ($x, $z))"
                }
            }
        }
    }


    object get {
        def apply(tup: Any, n: Int): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree, n: c.Tree): c.Tree = {
                val i = ExtractInt(c)(n)
                treeToList(c)(tup)(i)
            }
        }
    }


    object head {
        def apply(tup: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree): c.Tree = {
                treeToList(c)(tup).head
            }
        }
    }


    object tail {
        def apply(tup: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree): c.Tree = treeFromList(c) {
                treeToList(c)(tup).tail
            }
        }
    }


    object isEmpty {
        def apply(tup: Any): Boolean = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree): c.Tree = {
                val b = treeToList(c)(tup).isEmpty
                q"$b"
            }
        }
    }


    object length {
        def apply(tup: Any): Int = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree): c.Tree = {
                val n = treeToList(c)(tup).length
                q"$n"
            }
        }
    }


    object map {
        def apply(tup: Any, f: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree, f: c.Tree): c.Tree = treeFromList(c) {
                treeToList(c)(tup).map { x =>
                    q"${Here(c)}.apply($f, $x)"
                }
            }
        }
    }


    object append {
        def apply(tup: Any, x: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree, x: c.Tree): c.Tree = treeFromList(c) {
                treeToList(c)(tup) :+ x
            }
        }
    }


    object prepend {
        def apply(tup: Any, x: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree, x: c.Tree): c.Tree = treeFromList(c) {
                x :: treeToList(c)(tup)
            }
        }
    }


    object reduceLeft {
        def apply(tup: Any, f: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree, f: c.Tree): c.Tree = {
                treeToList(c)(tup).reduceLeft { (z, x) =>
                    q"${Here(c)}.apply($f, ($z, $x))"
                }
            }
        }
    }


    object reduceRight {
        def apply(tup: Any, f: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree, f: c.Tree): c.Tree = {
                treeToList(c)(tup).reduceRight { (x, z) =>
                    q"${Here(c)}.apply($f, ($x, $z))"
                }
            }
        }
    }


    object reverse {
        def apply(tup: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree): c.Tree = treeFromList(c) {
                treeToList(c)(tup).reverse
            }
        }
    }


    object toList {
        def apply(tup: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree): c.Tree = {
                val xs = treeToList(c)(tup)
                q"scala.List(..$xs)"
            }
        }
    }


    object transpose {
        def apply(tups: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tups: c.Tree): c.Tree = treeFromList(c) {
                treeToList(c)(tups).map { tup =>
                    treeToList(c)(tup)
                }.transpose.map { xs =>
                    q"(..$xs)"
                }
            }
        }
    }


    object updated {
        def apply(tup: Any, n: Int, x: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree, n: c.Tree, x: c.Tree): c.Tree = treeFromList(c) {
                val i = ExtractInt(c)(n)
                treeToList(c)(tup).updated(i, x)
            }
        }
    }


    object fromString {
        def apply(xs: String): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(xs: c.Tree): c.Tree = treeFromList(c) {
                xs match {
                    case q"${y: String}" => y.toList.map { c => q"$c" }
                }
            }
        }
    }
}
