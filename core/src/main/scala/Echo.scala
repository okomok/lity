

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Echo {
    def apply(x: Any): Unit = macro Impl.apply

    final class Impl(override val c: Context) extends InContext  {
        import c.universe._

        def apply(x: c.Tree): c.Tree = {
            c.echo(NoPosition, ShowExpr(c)(x))
            q"()"
        }
    }
}


object EchoRaw {
    def apply(x: Any): Unit = macro Impl.apply

    final class Impl(override val c: Context) extends InContext  {
        import c.universe._

        def apply(x: c.Tree): c.Tree = {
            c.echo(NoPosition, ShowExprRaw(c)(x))
            q"()"
        }
    }
}
