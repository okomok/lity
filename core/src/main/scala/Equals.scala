

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Equals {
    def apply(tup1: Any, tup2: Any): Any = macro EqualsImpl.apply
}


final class EqualsImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(tup1: c.Tree, tup2: c.Tree): c.Tree = {
        val xs1 = Tuple.toList(c)(tup1)
        val xs2 = Tuple.toList(c)(tup2)

        val b = xs1.corresponds(xs2) { (x1, x2) =>
            x1.equalsStructure(x2)
        }
        q"$b"
    }
}
