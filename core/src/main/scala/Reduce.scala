

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object ReduceLeft {
    def apply(tup: Any, ftup: Any): Any = macro ReduceLeftImpl.impl
}


final class ReduceLeftImpl(override val c: Context) extends InContext {
    import c.universe._

    def impl(tup: c.Tree, ftup: c.Tree): c.Tree = {
        val xs = Tuple.toList(c)(tup)
        val fs = Tuple.toList(c)(ftup)

        xs.reduceLeft { (z, x) =>
            fs.find { f =>
                x.tpe <:< Function2ParamType2(c)(f).tpe
            } match {
                case Some(f) => q"$f($z, $x)"
                case None => c.abort(c.enclosingPosition, "match error")
            }
        }
    }
}


object ReduceRight {
    def apply(tup: Any, ftup: Any): Any = macro ReduceRightImpl.impl
}


final class ReduceRightImpl(override val c: Context) extends InContext {
    import c.universe._

    def impl(tup: c.Tree, ftup: c.Tree): c.Tree = {
        val xs = Tuple.toList(c)(tup)
        val fs = Tuple.toList(c)(ftup)

        xs.reduceRight { (x, z) =>
            fs.find { f =>
                x.tpe <:< Function2ParamType1(c)(f).tpe
            } match {
                case Some(f) => q"$f($x, $z)"
                case None => c.abort(c.enclosingPosition, "match error")
            }
        }
    }
}
