

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Echo {
    def apply(x: Any): Unit = macro EchoImpl.impl
}


final class EchoImpl(override val c: Context) extends InContext  {
    import c.universe._

    def impl(x: c.Tree): c.Tree = {
        c.echo(NoPosition, show(x))
        q"()"
    }
}


object EchoRaw {
    def apply(x: Any): Unit = macro EchoRawImpl.impl
}


final class EchoRawImpl(override val c: Context) extends InContext  {
    import c.universe._

    def impl(x: c.Tree): c.Tree = {
        c.echo(NoPosition, showRaw(x))
        q"()"
    }
}
