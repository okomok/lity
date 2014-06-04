

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object ToList {
    def apply(tup: Any): Any = macro ToListImpl.impl
}


final class ToListImpl(override val c: Context) extends InContext {
    import c.universe._

    def impl(tup: c.Tree): c.Tree = {
        val xs = Tuple.toList(c)(tup)
        q"scala.List(..$xs)"
    }
}
