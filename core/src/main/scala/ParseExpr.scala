

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object ParseExpr {
    def apply(c: Context)(x: c.Tree): c.Tree = {
        c.parse {
            ExtractString(c)(x)
        }
    }
}
