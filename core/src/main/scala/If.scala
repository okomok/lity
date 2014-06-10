

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object `if` {
    def apply(b: Boolean, t: String, e: String): Any = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        def apply(b: c.Tree, t: c.Tree, e: c.Tree): c.Tree = ParseTree(c) {
            if (AsBoolean(c)(b)) t else e
        }
    }
}
