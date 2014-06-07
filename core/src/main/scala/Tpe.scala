

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object Tpe {
    def apply(c: Context)(x: c.Tree): c.Type = {
        import c.universe._
        c.typecheck(x).tpe
    }
}
