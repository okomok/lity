

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object ClassBy extends Macro {
    def apply(x: Any): Any = macro Impl.apply

    final class Impl(override val c: Context) extends MacroImpl1 with ReturnConstant1 {
        import c.universe._

        override protected def impl(x: c.Tree): c.Tree = {
            q"Predef.classOf[${x.tpe.widen}]"
        }
    }
}