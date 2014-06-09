

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity



object Fun_ {
    def apply(xs: Any*): Any = macro Tuple.RawImpl.apply
}


object Fun {
    def apply(xs: (Any, String)*): Any = macro FunImpl.apply
}


final class FunImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(xs: c.Tree*): c.Tree = q"${Here(c)}.Lit { ${Here(c)}.Fun_(..$xs) }"
}
