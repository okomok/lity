

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Map {
    def apply(tup: Any, f: Any): Any = macro MapImpl.impl
}


final class MapImpl(override val c: Context) extends InContext {
    import c.universe._

    def impl(tup: c.Tree, f: c.Tree): c.Tree = Tuple(c) {
        Tuple.toList(c)(tup).map { x =>
            Apply_(c)(f, x)
        }
    }
}
