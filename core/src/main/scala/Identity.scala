

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Identity {
    def apply(x: Any): Any = macro IdentityImpl.impl
}


final class IdentityImpl(override val c: Context) extends InContext {
    def impl(x: c.Tree): c.Tree = x
}
