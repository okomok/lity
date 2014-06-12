

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Error {
    def apply(msg: String): Unit = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._

        def apply(msg: c.Tree): c.Tree = {
            c.error(c.enclosingPosition, AsString(c)(msg))
            q"()"
        }
    }
}
