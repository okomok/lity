

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object parse {
    def apply(x: String): Any = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        def apply(x: c.Tree): c.Tree = ParseTree(c)(x)
    }
}


object unparse {
    def apply(x: Any): String = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        def apply(x: c.Tree): c.Tree = UnparseTree(c)(x)
    }
}
