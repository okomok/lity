

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object LCons_ {
    def apply(x: Any, xs: String) = (x, xs)
}


object LCons {
    def apply(x: Any, xs: String): Any = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._

        def apply(x: c.Tree, xs: c.Tree): c.Tree = {
//            RequireExpr(c)(xs)
            q"${Here(c)}.Unparse { ${Here(c)}.LCons_($x, $xs) }"
        }
    }
}
