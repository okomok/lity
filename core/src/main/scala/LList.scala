

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

    def head(tup: Any): Any = macro HeadImpl.apply

    final class HeadImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(tup: c.Tree): c.Tree = extract(c)(tup) match {
            case None => throw new Error("no such element")
            case Some((x, _)) => x
        }
    }

    def tail(tup: Any): Any = macro TailImpl.apply

    final class TailImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(tup: c.Tree): c.Tree = extract(c)(tup) match {
            case None => throw new Error("no such element")
            case Some((_, xs)) => xs
        }
    }


    def get(tup: Any, n: Int): Any = macro GetImpl.apply

    final class GetImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(tup: c.Tree, n: c.Tree): c.Tree = extract(c)(tup) match {
            case None => throw new Error("no such element")
            case Some((x, xs)) => Extract.Int(c)(n) match {
                case 0 => x
                case n => q"${Here(c)}.LList.get($xs, ${n-1})"
            }
        }
    }


    def rangeFrom(n: Int): Any = macro RangeFromImpl.apply

    final class RangeFromImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(n: c.Tree): c.Tree = {
            val xs = showCode(q"${Here(c)}.LList.rangeFrom($n + 1)")
            q"${Here(c)}.LCons($n, $xs)"
        }
    }
}
