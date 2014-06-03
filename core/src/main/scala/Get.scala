

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Get {
    def apply(tup: Any, n: Int): Any = macro GetImpl.impl
}


final class GetImpl(override val c: Context) extends InContext {
    import c.universe._

    def impl(tup: c.Tree, n: c.Tree): c.Tree = {
        val i = ExtractInt(c)(n)
        Tuple.toList(c)(tup)(i)
    }
}
