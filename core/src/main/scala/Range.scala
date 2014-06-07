

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Range {
    def apply(n: Any, m: Any): Any = macro RangeImpl.apply
}


final class RangeImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(n: c.Tree, m: c.Tree): c.Tree = Tuple(c) {
        List.range(Extract.Int(c)(n), Extract.Int(c)(m)).map { i =>
            q"$i"
        }
    }
}
