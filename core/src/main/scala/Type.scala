

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


sealed trait Type {
    type apply
}


object Type extends Macro {

    def wrap[x]: wrap[x] = new Type {
        override type apply = x
    }

    type wrap[x] = Type {
        type apply = x
    }


    def of[x](x: x): wrap[x] = wrap[x]


    def apply(x: Class[_]): Type = macro Impl.apply

    final class Impl(override val c: Context) extends MacroImpl1 {
        import c.universe._

        override protected def impl(x: c.Tree): c.Tree = {
            x match {
                case Literal(Constant(a: c.universe.Type)) => q"${Here(c)}.Type.wrap[$a]"
            }
        }
    }
}