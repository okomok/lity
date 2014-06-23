

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Compile extends Macro {
    def apply(x: String): Any = macro Impl.apply

    final class Impl(override val c: Context) extends MacroImpl1 with ConstantParam1 {
        override protected def impl(x: c.Tree): c.Tree = c.typecheck(ParseTree(c)(x))
    }
}


object Uncompile extends Macro {
    def apply(x: Any): String = macro Impl.apply

    final class Impl(override val c: Context) extends MacroImpl1 with ReturnConstant1 {
        override protected def impl(x: c.Tree): c.Tree = {
            UnparseTree(c)(x)
        }
    }
}
