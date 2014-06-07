

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object FoldLeft {
    def apply(tup: Any, z: Any, f: Any): Any = macro FoldLeftImpl.apply
}


final class FoldLeftImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(tup: c.Tree, z: c.Tree, f: c.Tree): c.Tree = {
        Tuple.toList(c)(tup).foldLeft(z) { (z, x) =>
            q"${Here(c)}.Apply($f, ($z, $x))"
        }
    }
}


object FoldRight {
    def apply(tup: Any, z: Any, f: Any): Any = macro FoldRightImpl.apply
}


final class FoldRightImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(tup: c.Tree, z: c.Tree, f: c.Tree): c.Tree = {
        Tuple.toList(c)(tup).foldRight(z) { (x, z) =>
            q"${Here(c)}.Apply($f, ($x, $z))"
        }
    }
}
