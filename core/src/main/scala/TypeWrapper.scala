

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


trait TypeWrapper {
    type unwrap
}


object TypeWrapper {
    def apply(c: Context)(x: c.Tree): c.Tree = {
        import c.universe._
        q"${Here(c)}.TypeWrapper.of[$x]"
    }

    def of[x]: of[x] = new TypeWrapper {
        override type unwrap = x
    }

    type of[x] = TypeWrapper {
        type unwrap = x
    }
}
