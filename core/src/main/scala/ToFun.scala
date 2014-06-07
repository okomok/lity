

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object ToFun {
    def apply(m: Any): Any = macro ToFunImpl.apply
}


final class ToFunImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(m: c.Tree): c.Tree = {
        val y = showCode(q"$m(_X1)")
        q"""
        ${Here(c)}.Fun(${Here(c)}._X1 -> $y)
        """
    }
}
