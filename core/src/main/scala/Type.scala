

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


sealed trait Type {
    type apply
}



object Type {
    // doesn't work for a bug of `showCode`.
    def of(x: Any): String = macro OfImpl.apply

    final class OfImpl(override val c: Context) extends InContext {
        import c.universe._

        def apply(x: c.Tree): c.Tree = {
            q"""
            ${Here(c)}.unparse { ${Here(c)}.Type.apply[${x.tpe}] }
            """
        }
    }

    def apply[x]: apply[x] = new Type {
        override type apply = x
    }

    type apply[x] = Type {
        type apply = x
    }

    def unwrap(c: Context)(x: c.Tree): c.Type = {
        import c.universe._
        x match {
            case q"${x: String}" => _unwrap(c)(c.parse(x))
            case _ => _unwrap(c)(x)
        }
    }

    private def _unwrap(c: Context)(x: c.Tree): c.Type = {
        import c.universe._
        x match {
            case q"${_}[$y](..${_})" => SafeTpe(c)(y)
            case _ => TypeError(c)("illegal argument", x, "Type")
        }
    }
}
