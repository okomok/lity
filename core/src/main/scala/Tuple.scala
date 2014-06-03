

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


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
            case Literal(Constant(x: String)) => _toList(c)(c.typecheck(c.parse(x)))
            case x => _toList(c)(x)
        }
    }

    private def _toList(c: Context)(tup: c.Tree): List[c.Tree] = {
        import c.universe._

        tup match {
            case Literal(Constant(())) => Nil
            case Apply(_, xs) => xs
        }
    }
}
