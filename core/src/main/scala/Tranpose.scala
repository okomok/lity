

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Transpose {
    def apply(tups: Any): Any = macro TransposeImpl.impl
}


final class TransposeImpl(override val c: Context) extends InContext {
    import c.universe._

    def impl(tups: c.Tree): c.Tree = Tuple(c) {
        Tuple.toList(c)(tups).map { tup =>
            Tuple.toList(c)(tup)
        }.transpose.map { xs =>
            q"(..$xs)"
        }
    }
}
