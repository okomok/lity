

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Append {
    def apply(tup1: Any, tup2: Any): Any = macro AppendImpl.impl
}


final class AppendImpl(override val c: Context) extends InContext {
    import c.universe._

    def impl(tup1: c.Tree, tup2: c.Tree): c.Tree = {
        val ys = Tuple.toList(c)(tup1) ++ Tuple.toList(c)(tup2)
        q"${Tuple(c)(ys)}"
    }
}
