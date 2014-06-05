

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object FoldLeft {
    def apply(tup: Any, z: Any, f: Any): Any = macro FoldLeftImpl.impl
}


final class FoldLeftImpl(override val c: Context) extends InContext {
    import c.universe._

    def impl(tup: c.Tree, z: c.Tree, f: c.Tree): c.Tree = {
        Tuple.toList(c)(tup).foldLeft(z) { (z, x) =>
            Apply_(c)(f, q"($z, $x)")
        }
    }
}


object FoldRight {
    def apply(tup: Any, z: Any, f: Any): Any = macro FoldRightImpl.impl
}


final class FoldRightImpl(override val c: Context) extends InContext {
    import c.universe._

    def impl(tup: c.Tree, z: c.Tree, f: c.Tree): c.Tree = {
        Tuple.toList(c)(tup).foldRight(z) { (x, z) =>
            Apply_(c)(f, q"($x, $z)")
        }
    }
}
