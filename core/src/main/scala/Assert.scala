

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Assert extends Macro {
    def apply(x: Any): Unit = macro Impl.apply

    final class Impl(override val c: Context) extends MacroImpl1 with ConstantParam1 {
        import c.universe._

        override protected def impl(x: c.Tree): c.Tree = Assertion(c)(q"true", x)
    }
}


object AssertNot extends Macro {
    def apply(x: Any): Unit = macro Impl.apply

    final class Impl(override val c: Context) extends MacroImpl1 with ConstantParam1 {
        import c.universe._

        override protected def impl(x: c.Tree): c.Tree = Assertion(c)(q"false", x)
    }
}
