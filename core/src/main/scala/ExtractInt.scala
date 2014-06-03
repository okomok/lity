

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object ExtractInt {
    def apply(c: Context)(x: c.Tree): Int = {
        import c.universe._

        x match {
            case Literal(Constant(y: Int)) => y
            case _ => CompileError.illegalArgument(c)(show(x) + " is required to be Int literal.")
        }
    }
}
