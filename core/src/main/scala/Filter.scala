

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Filter {
    def apply(tup: Any, ftup: Any): Any = macro FilterImpl.impl
}


final class FilterImpl(override val c: Context) extends InContext {
    import c.universe._

    def impl(tup: c.Tree, ftup: c.Tree): c.Tree = Tuple(c) {
        val xs = Tuple.toList(c)(tup)
        val fs = Tuple.toList(c)(ftup)

        xs.filter { x =>
            fs.find { f =>
                x.tpe <:< Type.unwrap(c)(f).tpe
            }.nonEmpty
        }
    }
}
