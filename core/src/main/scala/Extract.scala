

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object ExtractPair {
    def apply(c: Context)(x: c.Tree): (c.Tree, c.Tree) = {
        import c.universe._

        Tuple.treeToList(c)(x) match {
            case v :: w :: Nil => (v, w)
            case _ => TypeError(c)("illegal argument", x, "pair")
        }
    }
}


private object ExtractInt {
    def apply(c: Context)(x: c.Tree): Int = {
        import c.universe._

        x match {
            case q"${y: Int}" => y
            case _ => TypeError(c)("illegal argument", x, "Int literal")
        }
    }
}


private object ExtractLong {
    def apply(c: Context)(x: c.Tree): Long = {
        import c.universe._
        x match {
            case q"${y: Long}" => y
            case t => TypeError(c)("illegal argument", x, "Long literal")
        }
    }
}


private object ExtractString {
    def apply(c: Context)(x: c.Tree): String = {
        import c.universe._

        x match {
            case q"${y: String}" => y
            case _ => TypeError(c)("illegal argument", x, "String literal")
        }
    }
}
