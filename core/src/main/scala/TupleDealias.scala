

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object TupleDealias {
    def apply(c: Context)(tup: c.Tree): c.Tree = {
        import c.universe._

        tup match {
            case q"${_}($x).->[${_}]($y)" => q"($x, $y)"
            case _ => tup
        }
    }
}
