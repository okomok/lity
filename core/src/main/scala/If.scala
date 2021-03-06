

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object If extends Macro {
    def apply(b: Boolean, t: Any, e: Any): Any = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        def apply(b: c.Tree, t: c.Tree, e: c.Tree): c.Tree = {
            impl(Constantify(c)(b), t, e)
        }

        private def impl(b: c.Tree, t: c.Tree, e: c.Tree): c.Tree = {
            if (AsBoolean(c)(b)) t else e
        }
    }
}
