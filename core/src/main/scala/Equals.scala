

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Equals extends Macro {
    def apply(x: Any, y: Any): Boolean = macro Impl.apply

    final class Impl(override val c: Context) extends MacroImpl2 with ReturnConstant2 {
        import c.universe._

        override protected def impl(x: c.Tree, y: c.Tree): c.Tree = {
            val z = x.equalsStructure(y)
            q"$z"
        }
    }
}
