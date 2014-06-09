

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Echo {
    def apply(x: Any): Unit = macro Impl.apply

    final class Impl(override val c: Context) extends InContext  {
        import c.universe._

        def apply(x: c.Tree): c.Tree = {
            c.echo(NoPosition, show(x))
            q"()"
        }
    }
}


object EchoRaw {
    def apply(x: Any): Unit = macro Impl.apply

    final class Impl(override val c: Context) extends InContext  {
        import c.universe._

        def apply(x: c.Tree): c.Tree = {
            c.echo(NoPosition, showRaw(x))
            q"()"
        }
    }
}


object EchoType {
    def apply(x: Any): Unit = macro Impl.apply

    final class Impl(override val c: Context) extends InContext  {
        import c.universe._

        def apply(x: c.Tree): c.Tree = {
            c.echo(NoPosition, show(x.tpe.dealias))
            q"()"
        }
    }
}


object EchoTypeRaw {
    def apply(x: Any): Unit = macro Impl.apply

    final class Impl(override val c: Context) extends InContext  {
        import c.universe._

        def apply(x: c.Tree): c.Tree = {
            c.echo(NoPosition, showRaw(x.tpe.dealias))
            q"()"
        }
    }
}
