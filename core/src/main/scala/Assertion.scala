

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object Assertion {
    def apply(c: Context)(expected: c.Tree, x: c.Tree): c.Tree = {
        import c.universe._

        c.typecheck(expected)

        x match {
            case q"${s: String}" => _apply(c)(s, expected, c.typecheck(c.parse(s)))
            case q"${_: Boolean}" => _apply(c)(show(x), expected, x)
            case _ => TypeError(c)("illegal argument", x, "Boolean literal or String literal of expression")
        }
    }

    private def _apply(c: Context)(s: String, expected: c.Tree, x: c.Tree): c.Tree = {
        import c.universe._

        if (x.equalsStructure(expected)) {
            q"()"
        } else {
            throw new AssertionError(s"$s\n    expected:<${ShowExpr(c)(expected)}> but was:<${ShowExpr(c)(x)}>")
        }
    }
}
