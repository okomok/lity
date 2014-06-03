

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


sealed trait Type[T]


object Type {
    def apply[T] = new Type[T] {}

    def unwrap(c: Context)(x: c.Tree): c.Tree = {
        import c.universe._
        val TypeApply(_, List(y)) = x
        y
    }
}
