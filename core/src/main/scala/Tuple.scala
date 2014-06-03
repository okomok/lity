

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object Tuple {
    def apply(c: Context)(n: Int): c.Tree = {
        import c.universe._
        Ident(definitions.TupleClass(n).name.toTermName)
    }

    def toList(c: Context)(tup: c.Tree): List[c.Tree] = {
        import c.universe._
        val Apply(_, xs) = tup
        xs
    }
}
