

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object echo {
    def apply(x: Any): Unit = macro Impl.apply

    final class Impl(override val c: Context) extends InContext  {
        import c.universe._

        def apply(x: c.Tree): c.Tree = {
            c.echo(NoPosition, show(x))
            q"()"
        }
    }
}


object echoRaw {
    def apply(x: Any): Unit = macro Impl.apply

    final class Impl(override val c: Context) extends InContext  {
        import c.universe._

        def apply(x: c.Tree): c.Tree = {
            c.echo(NoPosition, showRaw(x))
            q"()"
        }
    }
}


object echoType {
    def apply(x: Any): Unit = macro Impl.apply

    final class Impl(override val c: Context) extends InContext  {
        import c.universe._

        def apply(x: c.Tree): c.Tree = {
            c.echo(NoPosition, show(x.tpe.dealias))
            q"()"
        }
    }
}


object echoTypeRaw {
    def apply(x: Any): Unit = macro Impl.apply

    final class Impl(override val c: Context) extends InContext  {
        import c.universe._

        def apply(x: c.Tree): c.Tree = {
            c.echo(NoPosition, showRaw(x.tpe.dealias))
            q"()"
        }
    }
}
