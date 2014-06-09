

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity



object Fun_ {
    def apply(xs: Any*): Any = macro Tuple.RawImpl.apply
}


object Fun {

    def apply(xs: (Any, String)*): Any = macro ApplyImpl.apply

    final class ApplyImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(xs: c.Tree*): c.Tree = q"${Here(c)}.Lit { ${Here(c)}.Fun_(..$xs) }"
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


