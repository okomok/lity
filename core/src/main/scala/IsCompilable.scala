

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


import scala.reflect.macros.{ParseException, TypecheckException}


object IsCompilable extends Macro {
    def apply(x: String): Boolean = macro Impl.apply

    final class Impl(override val c: Context) extends MacroImpl1 with ConstantParam1 with ReturnConstant1 {
        import c.universe._

        override protected def impl(x: c.Tree): c.Tree = {
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
