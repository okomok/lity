

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object conforms {
    def apply(x: Class[_], y: Class[_]): Boolean = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        def apply(x: c.Tree, y: c.Tree): c.Tree = {
            TypePredicate2(c)(x, y) { (a, b) =>
                a <:< b
            }
        }
    }
}
