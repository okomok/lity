

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object ShowExpr {
    def apply(c: Context)(x: c.Tree): String = {
        import c.universe._
        s"${show(x)}: ${show(c.typecheck(x).tpe.dealias)}"
    }
}


private object ShowExprRaw {
    def apply(c: Context)(x: c.Tree): String = {
        import c.universe._
        s"${showRaw(x)}: ${showRaw(c.typecheck(x).tpe.dealias)}"
    }
}
