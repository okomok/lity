

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


import scala.reflect.macros.TypecheckException


object Intercept extends Macro {
    def apply(r: String)(x: String): Unit = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._

        def apply(r: c.Tree)(x: c.Tree): c.Tree = {
            impl(Constantify(c)(r))(Constantify(c)(x))
        }

        private def impl(r: c.Tree)(x: c.Tree): c.Tree = {
            val rgx = AsString(c)(r)
            val code = AsString(c)(x)

            try {
                c.typecheck(c.parse(code))
                CompileError.unexpectedCompile(c)(show(x))
            } catch {
                case e: TypecheckException => {
                    if (!e.getMessage.matches(rgx)) {
                        CompileError.unexpectedError(c)(s"expected error:<$r> but was:<${e.getMessage}>")
                    }
                }
            }
            q"()"
        }
    }
}
