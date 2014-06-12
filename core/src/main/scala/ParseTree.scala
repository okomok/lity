

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object ParseTree {
    def apply(c: Context)(x: c.Tree): c.Tree = {
        c.parse {
            AsString(c)(x)
        }
    }
}


// Broken for SI-8447
private object UnparseTree {
    def apply(c: Context)(x: c.Tree): c.Tree = {
        import c.universe._
        q"${showCode(x)}"
    }
}
