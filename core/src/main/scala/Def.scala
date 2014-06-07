

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Def {
    def apply(xs: Any*): Any = macro DefImpl.apply
}


final class DefImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(xs: c.Tree*): c.Tree = q"${Here(c)}.Lit { (..$xs, ()) }"
}
