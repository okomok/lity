

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


// Credit to macrocosm


object Regex extends Macro {
    def apply(x: String): scala.util.matching.Regex = macro Impl.apply

    final class Impl(override val c: Context) extends MacroImpl1 with ConstantParam1 {
        import c.universe._

        override protected def impl(x: c.Tree): c.Tree = {
            val s = AsString(c)(x)
            s.r
            q"$s.r"
        }
    }
}
