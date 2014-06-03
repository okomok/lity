

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object P_ {
    def apply(x: String): Any = macro Parse.impl
}

object Parse {
    def apply(x: String): Any = macro Parse.impl
}


final class Parse(override val c: Context) extends UntypedMacroImpl {
    override protected def macroImpl(x: c.Tree): c.Tree = x
}
