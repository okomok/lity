

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object IsEmpty {
    def apply(tup: Any): Int = macro IsEmptyImpl.apply
}


final class IsEmptyImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(tup: c.Tree): c.Tree = {
        val b = Tuple.toList(c)(tup).isEmpty
        q"$b"
    }
}
