

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


sealed trait Type {
    type apply
}


object Type {
    def of[x](x: x): apply[x] = apply[x]

    def apply[x]: apply[x] = new Type {
        override type apply = x
    }

    type apply[x] = Type {
        type apply = x
    }

    def unwrap(c: Context)(x: c.Tree): c.Tree = {
        import c.universe._
        val TypeApply(_, List(y)) = x
        y
    }
}
