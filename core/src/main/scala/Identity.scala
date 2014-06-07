

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Identity {
    def apply(x: Any): Any = macro IdentityImpl.apply
}


final class IdentityImpl(override val c: Context) extends InContext {
    def apply(x: c.Tree): c.Tree = x
}
