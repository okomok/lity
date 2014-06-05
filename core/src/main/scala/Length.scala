

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Length {
    def apply(tup: Any): Any = macro LengthImpl.impl
}


final class LengthImpl(override val c: Context) extends InContext {
    import c.universe._

    def impl(tup: c.Tree): c.Tree = {
        val n = Tuple.toList(c)(tup).length
        q"$n"
    }
}
