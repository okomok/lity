

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Lambda {
    def apply(arg: Any*): Any = macro LambdaImpl.apply
}


final class LambdaImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(arg: c.Tree*): c.Tree = q"(..$arg, ())"
}
