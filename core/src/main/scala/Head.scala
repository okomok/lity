

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Head {
    def apply(tup: Any): Any = macro HeadImpl.impl
}


final class HeadImpl(override val c: Context) extends InContext {
    import c.universe._

    def impl(tup: c.Tree): c.Tree = {
        Tuple.toList(c)(tup).head
    }
}
