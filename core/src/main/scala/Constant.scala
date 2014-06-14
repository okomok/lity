

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Constant extends Macro {
    def apply(x: Any): Any = macro Impl.apply

    final class Impl(override val c: Context) extends MacroImpl1 {
        override protected def impl(x: c.Tree): c.Tree = x
    }
}
