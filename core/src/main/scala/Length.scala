

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Length {
    def apply(tup: Any): Any = macro LengthImpl.apply
}


final class LengthImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(tup: c.Tree): c.Tree = {
        val n = Tuple.toList(c)(tup).length
        q"$n"
    }
}
