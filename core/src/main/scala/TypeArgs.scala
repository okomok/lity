

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object TypeArgs {
    def apply(c: Context)(a: c.Type): List[c.Type] = {
        import c.universe._
        val TypeRef(_, _, bs) = a.dealias
        bs
    }
}
