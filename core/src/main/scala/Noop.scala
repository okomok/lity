

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Noop extends Macro {
    def apply(x: Any*): Unit = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._
        def apply(x: c.Tree*): c.Tree = EnsuringConstant(c) {
            q"()"
        }
    }
}
