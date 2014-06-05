

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Defer {
    def apply(x: Any): Any => Any = throw new Error("impossible")
}


private object Undefer {
    def apply(c: Context)(x: c.Tree): c.Tree = {
        import c.universe._
        TreeReplace(c)(x, q"${Here(c)}.Defer", q"${Here(c)}.Identity")
    }
}
