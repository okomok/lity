

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Date {
    def apply(): String = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._

        def apply(): c.Tree = {
            val y = new java.util.Date().toString
            q"$y"
        }
    }
}
