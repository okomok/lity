

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Transpose {
    def apply(tups: Any): Any = macro TransposeImpl.apply
}


final class TransposeImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(tups: c.Tree): c.Tree = Tuple(c) {
        Tuple.toList(c)(tups).map { tup =>
            Tuple.toList(c)(tup)
        }.transpose.map { xs =>
            q"(..$xs)"
        }
    }
}
