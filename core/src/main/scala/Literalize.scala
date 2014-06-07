

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Lit {
    def apply(x: Any): String = macro LitImpl.apply
}


final class LitImpl(override val c: Context) extends InContext {
    import c.universe._
    def apply(x: c.Tree): c.Tree = q"${showCode(x)}"
}


object Unlit {
    def apply(x: Any): String = macro ParseImpl.apply
}
