

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Defer {
    def apply(x: Any): Bottom = new Bottom{}
}


private object Undefer {
    def apply(c: Context)(x: c.Tree): c.Tree = {
        import c.universe._
        Tree.replace(c)(x, q"${Here(c)}.Defer", q"${Here(c)}.Identity")
    }
}
