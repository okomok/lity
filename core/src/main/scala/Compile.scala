

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object C_ {
    def apply(x: String): Any = macro Compile.impl
}

object Compile {
    def apply(x: String): Any = macro Compile.impl
}


final class Compile(override val c: Context) extends UntypedMacroImpl {
    override protected def macroImpl(x: c.Tree): c.Tree = c.typecheck(x)
}
