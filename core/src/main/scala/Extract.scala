

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object ExtractPair {
    def apply(c: Context)(x: c.Tree): (c.Tree, c.Tree) = {
        import c.universe._

        TupleToList(c)(x) match {
            case v :: w :: Nil => (v, w)
            case _ => CompileError.illegalArgument(c)(x, "pair")
        }
    }
}


private object ExtractInt {
    def apply(c: Context)(x: c.Tree): Int = {
        import c.universe._

        x match {
            case q"${y: Int}" => y
            case _ => CompileError.illegalArgument(c)(x, "Int literal")
        }
    }
}


private object ExtractLong {
    def apply(c: Context)(x: c.Tree): Long = {
        import c.universe._
        x match {
            case q"${y: Long}" => y
            case t => CompileError.illegalArgument(c)(x, "Long literal")
        }
    }
}


private object ExtractString {
    def apply(c: Context)(x: c.Tree): String = {
        import c.universe._

        x match {
            case q"${y: String}" => y
            case _ => CompileError.illegalArgument(c)(x, "String literal")
        }
    }
}
