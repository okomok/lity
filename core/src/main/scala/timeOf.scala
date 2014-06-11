

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object timeOf {
    def apply(x: String): scala.Any = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._

        def apply(x: c.Tree): c.Tree = {
            val y = ParseTree(c)(x)
            val start = System.currentTimeMillis
            c.typecheck(y)
            val elapsed = System.currentTimeMillis - start
            q"$elapsed"
        }
    }
}
