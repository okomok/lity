

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object F_ {
    def apply(arg: Any): Any = macro FunImpl.apply
}

object Fun {
    def apply(arg: Any): Any = macro FunImpl.apply
}


final class FunImpl(override val c: Context) extends MacroImpl {
    import c.universe._

    override protected def name: c.Tree = q"${Here(c)}.Fun"
    override protected def impl(arg: c.Tree): c.Tree = {
        q"${Here(c)}.L_ { ( (${Here(c)}._X1, $arg(_X1)), () ) }"
    }
}
