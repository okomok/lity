

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Assert {
    def apply(x: Any): Unit = macro Impl.apply

    final class Impl(val c: Context) {
        import c.universe._

        def apply(x: c.Tree): c.Tree = {
            x match {
                case q"${s: String}" => _apply(s, c.typecheck(c.parse(s)))
                case q"${_: Boolean}" => _apply(show(x), x)
                case _ => TypeError(c)("illegal argument", x, "Boolean literal or String literal of expression")
            }
        }

        private def _apply(s: String, x: c.Tree): c.Tree = {
            x match {
                case q"true" => q"()"
                case y => throw new AssertionError(s"$s\n    expected:<${ShowExpr(c)(q"true")}> but was:<${ShowExpr(c)(y)}>")
            }
        }
    }
}


object AssertNot {
    def apply(x: Any): Unit = macro Impl.apply

    final class Impl(val c: Context) {
        import c.universe._

        def apply(x: c.Tree): c.Tree = {
            x match {
                case q"${s: String}" => _apply(s, c.typecheck(c.parse(s)))
                case q"${_: Boolean}" => _apply(show(x), x)
                case _ => TypeError(c)("illegal argument", x, "Boolean literal or String literal of expression")
            }
        }

        private def _apply(s: String, x: c.Tree): c.Tree = {
            x match {
                case q"false" => q"()"
                case y => throw new AssertionError(s"$s\n    expected:<${ShowExpr(c)(q"false")}> but was:<${ShowExpr(c)(y)}>")
            }
        }
    }
}
