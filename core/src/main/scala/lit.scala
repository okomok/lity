

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object lit {
    def apply(x: Any): Any = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._

        def apply(x: c.Tree): c.Tree = {
            x match {
                case q"()" => UnparseTree(c)(x)
                case Literal(Constant(_)) => x
                case x => UnparseTree(c)(x)
            }
        }
    }
}


// String is not supported.
object unlit {
    def apply(x: Any): Any = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._
        def apply(x: c.Tree): c.Tree = {
            x match {
                case q"${_: String}" => ParseTree(c)(x)
                case x => x
            }
        }
    }
}
