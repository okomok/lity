

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Concat {
    def apply(tup1: Any, tup2: Any): Any = macro ConcatImpl.impl
}


final class ConcatImpl(override val c: Context) extends InContext {
    import c.universe._

    def impl(tup1: c.Tree, tup2: c.Tree): c.Tree = Tuple(c) {
        Tuple.toList(c)(tup1) ++ Tuple.toList(c)(tup2)
    }
}
