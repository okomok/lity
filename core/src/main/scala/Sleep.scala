

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Sleep {
    def apply(x: Long): Unit = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._

        def apply(x: c.Tree): c.Tree = {
            Thread.sleep(ExtractLong(c)(x))
            q"()"
        }
    }
}
