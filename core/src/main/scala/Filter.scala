

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Filter {
    def apply(tup: Any, f: Any): Any = macro FilterImpl.apply
}


final class FilterImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(tup: c.Tree, f: c.Tree): c.Tree = Tuple(c) {
        Tuple.toList(c)(tup).filter { x =>
            AsBoolean(c)(q"${Here(c)}.Apply($f, $x)")
        }
    }
}
