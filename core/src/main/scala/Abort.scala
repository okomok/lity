

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Abort extends Macro {
    def apply(x: String): Nothing = macro Impl.apply

    final class Impl(override val c: Context) extends MacroImpl1 with ConstantParam1 {
        import c.universe._

        override protected def impl(x: c.Tree): c.Tree = {
            c.abort(NoPosition, AsString(c)(x))
        }
    }
}
