

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Filter {
    def apply(tup: Any, f: Any): Any = macro FilterImpl.impl
}


final class FilterImpl(override val c: Context) extends InContext {
    import c.universe._

    def impl(tup: c.Tree, f: c.Tree): c.Tree = Tuple(c) {
        Tuple.toList(c)(tup).filter { x =>
            AsBoolean(c)(Call(c)(f, x))
        }
    }
}
