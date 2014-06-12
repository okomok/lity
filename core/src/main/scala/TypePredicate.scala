

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object TypePredicate2 {
    def apply(c: Context)(x: c.Tree, y: c.Tree)(pred: (c.Type, c.Type) => Boolean): c.Tree = {
        import c.universe._

        val z = x match {
            case Literal(Constant(a: Type)) => y match {
                case Literal(Constant(b: Type)) => pred(a, b)
                case _ => TypeError(c)("illegal argument", y, "Class literal")
            }
            case _ => TypeError(c)("illegal argument", x, "Class literal")
        }
        q"$z"
    }
}
