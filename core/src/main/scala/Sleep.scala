

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Sleep extends Macro {
    def apply(x: Long): Unit = macro Impl.apply

    final class Impl(override val c: Context) extends MacroImpl1 {
        import c.universe._

        override protected def impl(x: c.Tree): c.Tree = {
            Thread.sleep(AsLong(c)(x))
            q"()"
        }
    }
}
