

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object SafeTpe {
    def apply(c: Context)(x: c.Tree): c.Type = {
        c.typecheck(x).tpe
    }
}
