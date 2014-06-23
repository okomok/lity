

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Elapsed extends Macro {
    def apply(x: String): Long = macro Impl.apply

    final class Impl(override val c: Context) extends MacroImpl1 with ConstantParam1 with ReturnConstant1 {
        import c.universe._

        override protected def impl(x: c.Tree): c.Tree = {
            val y = ParseTree(c)(x)
            val start = System.currentTimeMillis
            c.typecheck(y)
            val elapsed = System.currentTimeMillis - start
            q"$elapsed"
        }
    }
}
