

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object ExtractString {
    def apply(c: Context)(x: c.Tree): String = {
        import c.universe._

        x match {
            case Literal(Constant(s: String)) => s
            case _ => CompileError.illegalArgument(c)(show(x) + " is required to be string literal.")
        }
    }
}
