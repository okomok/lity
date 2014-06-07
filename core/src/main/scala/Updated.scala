

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Updated {
    def apply(tup: Any, n: Int, x: Any): Any = macro UpdatedImpl.apply
}


final class UpdatedImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(tup: c.Tree, n: c.Tree, x: c.Tree): c.Tree = Tuple(c) {
        val i = Extract.Int(c)(n)
        Tuple.toList(c)(tup).updated(i, x)
    }
}
