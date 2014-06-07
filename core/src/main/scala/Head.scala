

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Head {
    def apply(tup: Any): Any = macro HeadImpl.apply
}


final class HeadImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(tup: c.Tree): c.Tree = {
        Tuple.toList(c)(tup).head
    }
}
