

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Append {
    def apply(arg1: Any, arg2: Any): Any = macro AppendImpl.apply
}


final class AppendImpl(override val c: Context) extends MacroImpl2 {
    import c.universe._

    override def name: c.Tree = q"${Here(c)}.Append"
    override def impl(tup1: c.Tree, x: c.Tree): c.Tree = Tuple(c) {
        Tuple.toList(c)(tup1) :+ x
    }
}
