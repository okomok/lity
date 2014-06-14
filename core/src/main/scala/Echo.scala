

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Echo extends Macro {
    def apply(x: String): Unit = macro Impl.apply

    final class Impl(override val c: Context) extends MacroImpl1  {
        import c.universe._

        override protected def impl(x: c.Tree): c.Tree = {
            c.echo(NoPosition, AsString(c)(x))
            q"()"
        }
    }
}


object EchoExpr extends Macro {
    def apply(x: Any): Unit = macro Impl.apply

    final class Impl(override val c: Context) extends MacroImpl1  {
        import c.universe._

        override protected def impl(x: c.Tree): c.Tree = {
            c.echo(NoPosition, ShowExpr(c)(x))
            q"()"
        }
    }
}


object EchoExprRaw extends Macro {
    def apply(x: Any): Unit = macro Impl.apply

    final class Impl(override val c: Context) extends MacroImpl1  {
        import c.universe._

        override protected def impl(x: c.Tree): c.Tree = {
            c.echo(NoPosition, ShowExprRaw(c)(x))
            q"()"
        }
    }
}
