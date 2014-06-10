

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object isConstant {
    def apply(x: Any): Boolean = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._

        def apply(x: c.Tree): c.Tree = x match {
            case Literal(Constant(_)) => q"true"
            case _ => q"false"
        }
    }
}
