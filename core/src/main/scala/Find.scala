

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Find {
    def apply(tup: Any, f: Any): Any = macro FindImpl.impl
}


final class FindImpl(override val c: Context) extends InContext {
    import c.universe._

    def impl(tup: c.Tree, f: c.Tree): c.Tree = {
        val y = Tuple.toList(c)(tup).find { x =>
            AsBoolean(c)(Call(c)(f, x))
        }
        q"$y"
    }
}
