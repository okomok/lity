

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Empty {
    def apply(tup: Any): Unit = macro EmptyImpl.apply
}


final class EmptyImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(tup: c.Tree): c.Tree = {
        q"()"
    }
}
