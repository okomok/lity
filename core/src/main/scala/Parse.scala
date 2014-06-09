

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Parse {
    def apply(x: String): Any = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        def apply(x: c.Tree): c.Tree = ParseTree(c)(x)
    }
}


object Unparse {
    def apply(x: Any): String = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._
        def apply(x: c.Tree): c.Tree = q"${showCode(x)}"
    }
}
