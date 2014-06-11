

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Duplicate {
    def apply(c: Context)(x: c.Tree): c.Tree = {
        import c.universe._
        c.parse(showCode(x))
    }
}
