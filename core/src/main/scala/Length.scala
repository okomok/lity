

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Length {
    def apply(arg: Any): Any = macro LengthImpl.apply
}


final class LengthImpl(override val c: Context) extends MacroImpl1 {
    import c.universe._

    override protected def name: c.Tree = q"${Here(c)}.Length"
    override protected def impl(arg: c.Tree): c.Tree = {
        val n = Tuple.toList(c)(arg).length
        q"$n"
    }
}
