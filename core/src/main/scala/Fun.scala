

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object F_ {
    def apply(m: Any): Any = macro FunImpl.apply
}

object Fun {
    def apply(m: Any): Any = macro FunImpl.apply
}


final class FunImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(m: c.Tree): c.Tree = {
        val y = showCode(q"$m(_X1)")
        q"""
        ${Here(c)}.Lit { ( (${Here(c)}._X1, $y), () ) }
        """
    }
}
