

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Date extends Macro {
    def apply(): String = macro Impl.apply

    final class Impl(override val c: Context) extends MacroImpl0 {
        import c.universe._

        override protected def impl(): c.Tree = {
            val y = new java.util.Date().toString
            q"$y"
        }
    }
}
