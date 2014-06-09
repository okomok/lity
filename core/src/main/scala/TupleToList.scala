

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object TupleToList {
    def apply(c: Context)(tup: c.Tree): List[c.Tree] = {
        import c.universe._

        tup match {
            case q"${x: String}" => _apply(c)(c.typecheck(c.parse(x)))
            case x => _apply(c)(x)
        }
    }

    private def _apply(c: Context)(tup: c.Tree): List[c.Tree] = {
        import c.universe._

        TupleDealias(c)(tup) match {
            case q"()" => Nil
            case q"${_}(..$xs)" => xs
            case _ => CompileError.illegalArgument(c)(tup, "tuple")
        }
    }
}
