

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Reverse {
    def apply(tup: Any): Any = macro ReverseImpl.impl
}


final class ReverseImpl(override val c: Context) extends InContext {
    import c.universe._

    def impl(tup: c.Tree): c.Tree = Tuple(c) {
        Tuple.toList(c)(tup).reverse
    }
}
