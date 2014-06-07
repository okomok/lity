

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Map {
    def apply(tup: Any, f: Any): Any = macro MapImpl.apply
}


final class MapImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(tup: c.Tree, f: c.Tree): c.Tree = Tuple(c) {
        Tuple.toList(c)(tup).map { x =>
            q"${Here(c)}.Apply($f, $x)"
        }
    }
}
