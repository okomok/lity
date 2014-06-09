

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Expr {
    def apply(x: String): Any = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        def apply(x: c.Tree): c.Tree = {
            ParseTree(c)(x)
            x
        }
    }
}
