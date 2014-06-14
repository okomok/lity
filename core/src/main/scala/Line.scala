

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Line extends Macro {
    def apply(): Int = macro Impl.apply

    final class Impl(override val c: Context) extends MacroImpl0 {
        import c.universe._

        override protected def impl(): c.Tree = {
            val y = c.macroApplication.pos.line
            q"$y"
        }
    }
}
