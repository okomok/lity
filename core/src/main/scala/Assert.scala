

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Assert {
    def apply(x: Any): Unit = macro Impl.apply

    final class Impl(val c: Context) {
        import c.universe._
        def apply(x: c.Tree): c.Tree = Assertion(c)(q"true", x)
    }
}


object AssertNot {
    def apply(x: Any): Unit = macro Impl.apply

    final class Impl(val c: Context) {
        import c.universe._
        def apply(x: c.Tree): c.Tree = Assertion(c)(q"false", x)
    }
}
