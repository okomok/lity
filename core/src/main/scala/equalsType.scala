

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object equalsType {
    def apply(x: Class[_], y: Class[_]): Boolean = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._

        def apply(x: c.Tree, y: c.Tree): c.Tree = {
            val z = x match {
                case Literal(Constant(a: Type)) => y match {
                    case Literal(Constant(b: Type)) => a =:= b
                    case y => TypeError(c)("illegal argument", y, "Type literal")
                }
                case x => TypeError(c)("illegal argument", x, "Type literal")
            }
            q"$z"
        }
    }
}
