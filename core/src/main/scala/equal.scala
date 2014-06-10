

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object equal {
    def apply(x: Any, y: Any): Boolean = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._
        def apply(x: c.Tree, y: c.Tree): c.Tree = q"${x.equalsStructure(y)}"
    }
}
