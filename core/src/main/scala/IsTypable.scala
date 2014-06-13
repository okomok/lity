

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


import scala.reflect.macros.TypecheckException


object IsTypable {
    def apply(x: String): Boolean = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._

        def apply(x: c.Tree): c.Tree = {
            try {
                c.typecheck(ParseTree(c)(x))
                q"true"
            } catch {
                case _: TypecheckException => q"false"
            }
        }
    }
}
