

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


sealed trait Type {
    type unwrap
}


object Type {
    def wrap[x]: wrap[x] = new Type {
        override type unwrap = x
    }

    type wrap[x] = Type {
        type unwrap = x
    }

    def of[x](x: x): wrap[x] = wrap[x]
}