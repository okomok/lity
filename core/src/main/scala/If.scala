

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object If {
    def apply(b: Boolean, t: String, e: String): Any = macro IfImpl.apply
}


final class IfImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(b: c.Tree, t: c.Tree, e: c.Tree): c.Tree = c.parse {
        Extract.String(c) {
            if (AsBoolean(c)(b)) t else e
        }
    }
}
