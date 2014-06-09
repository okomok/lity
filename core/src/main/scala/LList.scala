

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


// List(x1, x2) ~ (x1, """(x2, "()")""")


object LList {

    private def extract(c: Context)(tup: c.Tree): Option[(c.Tree, c.Tree)] = {
        import c.universe._

        TupleToList(c)(tup) match {
            case Nil => None
            case x :: q"${y: String}" :: Nil => Some((x, c.parse(y)))
            case _ => CompileError.illegalArgument(c)(tup, "two-element tuple")
        }
    }


    object head {
        def apply(tup: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree): c.Tree = extract(c)(tup) match {
                case None => throw new Error("no such element")
                case Some((x, _)) => x
            }
        }
    }


    object tail {
        def apply(tup: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree): c.Tree = extract(c)(tup) match {
                case None => throw new Error("no such element")
                case Some((_, xs)) => xs
            }
        }
    }


    object get {
        def apply(tup: Any, n: Int): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree, n: c.Tree): c.Tree = extract(c)(tup) match {
                case None => throw new Error("no such element")
                case Some((x, xs)) => ExtractInt(c)(n) match {
                    case 0 => x
                    case n => q"${Here(c)}.LList.get($xs, ${n-1})"
                }
            }
        }
    }


    object rangeFrom {
        def apply(n: Int): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(n: c.Tree): c.Tree = {
                val xs = showCode(q"${Here(c)}.LList.rangeFrom($n + 1)")
                q"${Here(c)}.LCons($n, $xs)"
            }
        }
    }
}
