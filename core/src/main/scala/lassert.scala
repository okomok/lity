

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object lassert {
    def apply(x: Any): Unit = macro Impl.apply

    final class Impl(val c: Context) {
        import c.universe._

        def apply(x: c.Tree): c.Tree = {
            UndeferTree(c)(x) match {
                case q"true" => q"()"
                case y => throw new AssertionError(s"<$x>\n    expected:<true: Boolean(true)> but was:<$y: ${y.tpe.dealias}>")
            }
        }
    }
}


object lassertNot {
    def apply(x: Any): Unit = macro Impl.apply

    final class Impl(val c: Context) {
        import c.universe._

        def apply(x: c.Tree): c.Tree = {
            UndeferTree(c)(x) match {
                case q"false" => q"()"
                case y => throw new AssertionError(s"<$x>\n    expected:<false: Boolean(false)> but was:<$y: ${y.tpe.dealias}>")
            }
        }
    }
}
