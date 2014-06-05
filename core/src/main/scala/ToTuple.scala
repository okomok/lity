

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object ToTuple {
    def apply(x: Any): Any = macro ToTupleImpl.impl
}


final class ToTupleImpl(override val c: Context) extends InContext {
    import c.universe._

    def impl(x: c.Tree): c.Tree = Tuple(c) {
        x match {
            case q"${y: String}" => y.toList.map { c => q"$c" }
        }
    }
}
