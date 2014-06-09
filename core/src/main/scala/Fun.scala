

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Fun_ {
    def apply(xs: Any*): Any = macro Tuple.raw.Impl.apply
}


object Fun {

    def apply(es: (Any, String)*): Any = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._

        def apply(es: c.Tree*): c.Tree = {
//            requireLegal(es: _*)
            q"""
            ${Here(c)}.Lit { ${Here(c)}.Fun_(..$es) }
            """
        }

        private def requireLegal(es: c.Tree*): Unit = {
            es.foreach { x_y =>
                ExtractPair(c)(x_y) match {
                    case (x, y) => RequireExpr(c)(y)
                }
            }
        }
    }


    object fromMacro {
        def apply(m: Any): Any = macro FromMacroImpl.apply
    }

    final class FromMacroImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(m: c.Tree): c.Tree = {
            val y = showCode(q"$m(_X1)")
            q"""
            ${Here(c)}.Fun(${Here(c)}._X1 -> $y)
            """
        }
    }
}
