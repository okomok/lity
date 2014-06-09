

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


import scala.reflect.macros.whitebox.Context


object AssertConstant {
    def apply(x: Any): Unit = macro Impl.apply

    final class Impl(val c: Context) {
        import c.universe._

        def apply(x: c.Tree): c.Tree = x match {
            case Literal(Constant(_)) => q"()"
            case _ => throw new AssertionError(s"${show(x)} is required to be a constant literal")
        }
    }
}