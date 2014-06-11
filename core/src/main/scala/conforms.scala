

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object conforms {
    def apply(x: Class[_], y: Class[_]): Boolean = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._

        def apply(x: c.Tree, y: c.Tree): c.Tree = {
            val z = (x, y) match {
                case (Literal(Constant(a: Type)), Literal(Constant(b: Type))) => a <:< b
//              case (q"${a: Type}", q"${b: Type}") => a <:< b
            }
            q"$z"
        }
    }
}
