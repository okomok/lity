

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object ToTuple {
    def apply(xs: Any): Any = macro ToTupleImpl.apply
}


final class ToTupleImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(xs: c.Tree): c.Tree = Tuple(c) {
        xs match {
            case q"${y: String}" => y.toList.map { c => q"$c" }
        }
    }
}
