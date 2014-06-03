

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Tail {
    def apply(tup: Any): Any = macro TailImpl.impl
}


final class TailImpl(override val c: Context) extends InContext {
    import c.universe._

    def impl(tup: c.Tree): c.Tree = {
        val ys = Tuple.toList(c)(tup).tail
        q"${Tuple(c)(ys)}"
    }
}
