

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object TypeError {
    def apply(c: Context)(msg: String, x: c.Tree, t: String): Nothing = {
        import c.universe._

        c.abort(c.enclosingPosition, s"""
            |$msg: $t is required, but
            |    ${show(x)}: ${show(SafeTpe(c)(x))}
            """.stripMargin)
    }
}
