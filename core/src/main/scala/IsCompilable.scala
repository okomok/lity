

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


import scala.reflect.macros.{ParseException, TypecheckException}


object IsCompilable {
    def apply(x: String): Boolean = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._

        def apply(x: c.Tree): c.Tree = {
            try {
                c.typecheck(ParseTree(c)(x))
                q"true"
            } catch {
                case _: ParseException => q"false"
                case _: TypecheckException => q"false"
            }
        }
    }
}
