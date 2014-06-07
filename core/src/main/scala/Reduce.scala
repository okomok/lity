

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object ReduceLeft {
    def apply(tup: Any, f: Any): Any = macro ReduceLeftImpl.apply
}


final class ReduceLeftImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(tup: c.Tree, f: c.Tree): c.Tree = {
        Tuple.toList(c)(tup).reduceLeft { (z, x) =>
            Apply_(c)(f, q"($z, $x)")
        }
    }
}


object ReduceRight {
    def apply(tup: Any, f: Any): Any = macro ReduceRightImpl.apply
}


final class ReduceRightImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(tup: c.Tree, f: c.Tree): c.Tree = {
        Tuple.toList(c)(tup).reduceRight { (x, z) =>
            Apply_(c)(f, q"($x, $z)")
        }
    }
}
