

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object LCons {
    def apply(x: Any, xs: String): Any = macro LConsImpl.apply
}


final class LConsImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(x: c.Tree, xs: c.Tree): c.Tree = {
        q"${Here(c)}.Lit { ($x, $xs) }"
    }
}
