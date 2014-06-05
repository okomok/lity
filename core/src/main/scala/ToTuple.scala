

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object ToTuple {
    def apply(arg: Any): Any = macro ToTupleImpl.apply
}


final class ToTupleImpl(override val c: Context) extends MacroImpl {
    import c.universe._

    override protected def name: c.Tree = q"${Here(c)}.ToTuple"
    override protected def impl(arg: c.Tree): c.Tree = Tuple(c) {
        arg match {
            case q"${y: String}" => y.toList.map { c => q"$c" }
        }
    }
}
