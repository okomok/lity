

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Abort {
    def apply(msg: String): Nothing = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        def apply(msg: c.Tree): c.Tree = {
            c.abort(c.enclosingPosition, AsString(c)(msg))
        }
    }
}
