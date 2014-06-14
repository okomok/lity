

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Always {
    def apply(x: Any)(y: Any*): Any = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        def apply(x: c.Tree)(y: c.Tree*): c.Tree = Constantify(c)(x)
    }
}
