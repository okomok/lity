

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object FoldLeft {
    def apply(tup: Any, z: Any, ftup: Any): Any = macro FoldLeftImpl.impl
}


final class FoldLeftImpl(override val c: Context) extends InContext {
    import c.universe._

    def impl(tup: c.Tree, z: c.Tree, ftup: c.Tree): c.Tree = {
        val xs = Tuple.toList(c)(tup)
        val fs = Tuple.toList(c)(ftup)

        xs.foldLeft(z) { (z, x) =>
            fs.find { f =>
                x.tpe <:< Function2ParamType2(c)(f).tpe
            } match {
                case Some(f) => q"$f($z, $x)"
                case None => c.abort(c.enclosingPosition, "match error")
            }
        }
    }
}


object FoldRight {
    def apply(tup: Any, z: Any, ftup: Any): Any = macro FoldRightImpl.impl
}


final class FoldRightImpl(override val c: Context) extends InContext {
    import c.universe._

    def impl(tup: c.Tree, z: c.Tree, ftup: c.Tree): c.Tree = {
        val xs = Tuple.toList(c)(tup)
        val fs = Tuple.toList(c)(ftup)

        xs.foldRight(z) { (x, z) =>
            fs.find { f =>
                x.tpe <:< Function2ParamType1(c)(f).tpe
            } match {
                case Some(f) => q"$f($x, $z)"
                case None => c.abort(c.enclosingPosition, "match error")
            }
        }
    }
}
