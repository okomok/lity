

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


// Note:
//   * `()` is not a constant expression in SLS.
//   *  The scalac can't make `Array(..)` a constant expression.


object IsConstant {
    def apply(x: Any): Boolean = macro Impl.apply

    final class Impl(override val c: Context) extends MacroImpl1 with ReturnConstant1{
        import c.universe._

        override protected def impl(x: c.Tree): c.Tree = {
            if (IsConstantTree(c)(x)) {
                q"true"
            } else {
                q"false"
            }
        }
    }
}


private object IsConstantTree {
    def apply(c: Context)(x: c.Tree): Boolean = {
        import c.universe._

        x match {
            case Literal(Constant(_: Unit)) => false
            case Literal(Constant(_)) => true
            case _ => false
        }
    }
}
