

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


sealed trait Type {
    type apply
}


object Type {
    def wrap[x]: wrap[x] = new Type {
        override type apply = x
    }

    type wrap[x] = Type {
        type apply = x
    }

    def of[x](x: => x): wrap[x] = wrap[x]
    def apply[x](x: Class[x]): wrap[x] = wrap[x]
}