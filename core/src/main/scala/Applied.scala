

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Applied {
    def apply(tup: Any, f: Any): Any = macro AppliedImpl.impl
}


final class AppliedImpl(override val c: Context) extends InContext {
    import c.universe._

    def impl(tup: c.Tree, f: c.Tree): c.Tree = {
        val xs = Tuple.toList(c)(tup)
        q"$f(..$xs)"
    }
}
