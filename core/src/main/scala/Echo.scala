

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object echoTree {
    def apply(x: Any): Unit = macro Impl.apply

    final class Impl(override val c: Context) extends InContext  {
        import c.universe._

        def apply(x: c.Tree): c.Tree = {
            c.echo(NoPosition, ShowExpr(c)(x))
            q"()"
        }
    }
}


object echoTreeRaw {
    def apply(x: Any): Unit = macro Impl.apply

    final class Impl(override val c: Context) extends InContext  {
        import c.universe._

        def apply(x: c.Tree): c.Tree = {
            c.echo(NoPosition, ShowExprRaw(c)(x))
            q"()"
        }
    }
}
