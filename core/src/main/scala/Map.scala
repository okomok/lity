

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Map {
    def apply(tup: Any, ftup: Any): Any = macro MapImpl.impl
}


final class MapImpl(override val c: Context) extends InContext {
    import c.universe._

    def impl(tup: c.Tree, ftup: c.Tree): c.Tree = Tuple(c) {
        val xs = Tuple.toList(c)(tup)
        val fs = Tuple.toList(c)(ftup)

        xs.map { x =>
            fs.find { f =>
                x.tpe <:< Function1ParamType1(c)(f).tpe
            } match {
                case Some(f) => q"$f($x)"
                case None => c.abort(c.enclosingPosition, "match error")
            }
        }
    }
}
