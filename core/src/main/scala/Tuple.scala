

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


trait Tuple {
    def apply[x1](x1: x1): Tuple1[x1] = Tuple1(x1)
    def apply[x1, x2](x1: x1, x2: x2): Tuple2[x1, x2] = Tuple2(x1, x2)
    def apply[x1, x2, x3](x1: x1, x2: x2, x3: x3): Tuple3[x1, x2, x3] = Tuple3(x1, x2, x3)
    def apply[x1, x2, x3, x4](x1: x1, x2: x2, x3: x3, x4: x4): Tuple4[x1, x2, x3, x4] = Tuple4(x1, x2, x3, x4)
}


private object Tuple {
    def apply(c: Context)(xs: List[c.Tree]): c.Tree = {
        import c.universe._

        if (xs.isEmpty) {
            q"()"
        } else {
            val tn = TermName(s"Tuple${xs.length}")
            q"scala.$tn(..$xs)"
        }
    }

    def toList(c: Context)(tup: c.Tree): List[c.Tree] = {
        import c.universe._

        tup match {
            case q"()" => Nil
            case q"${x: String}" => _toList(c)(c.typecheck(c.parse(x)))
            case x => _toList(c)(x)
        }
    }

    def normalize(c: Context)(tup: c.Tree): c.Tree = {
        import c.universe._

        tup match {
            case q"${_}($x).->[${_}]($y)" => q"($x, $y)"
            case _ => tup
        }
    }

    private def _toList(c: Context)(tup: c.Tree): List[c.Tree] = {
        import c.universe._

        normalize(c)(tup) match {
            case q"${_}(..$xs)" => xs
            case _ => CompileError.illegalArgument(c)(tup, "tuple")
        }
    }
}
