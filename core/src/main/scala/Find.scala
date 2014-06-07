

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Find {
    def apply(tup: Any, f: Any): Any = macro FindImpl.apply
}


final class FindImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(tup: c.Tree, f: c.Tree): c.Tree = {
        val y = Tuple.toList(c)(tup).find { x =>
            AsBoolean(c)(q"${Here(c)}.Apply($f, $x)")
        }
        q"$y"
    }
}
