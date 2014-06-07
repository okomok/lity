

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Prepend {
    def apply(tup1: Any, x: Any): Any = macro PrependImpl.apply
}


final class PrependImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(tup1: c.Tree, x: c.Tree): c.Tree = Tuple(c) {
        x :: Tuple.toList(c)(tup1)
    }
}
