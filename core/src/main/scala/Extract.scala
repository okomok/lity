

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object Extract {
    def Int(c: Context)(x: c.Tree): Int = {
        import c.universe._

        x match {
            case q"${y: Int}" => y
            case _ => CompileError.illegalArgument(c)(x, "Int literal")
        }
    }

    def Long(c: Context)(x: c.Tree): Long = {
        import c.universe._
        x match {
            case q"${y: Long}" => y
            case t => CompileError.illegalArgument(c)(x, "Long literal")
        }
    }

    def String(c: Context)(x: c.Tree): String = {
        import c.universe._

        x match {
            case q"${y: String}" => y
            case _ => CompileError.illegalArgument(c)(x, "String literal")
        }
    }
}
