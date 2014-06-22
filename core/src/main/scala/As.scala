

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object AsBoolean {
    def apply(c: Context)(x: c.Tree): Boolean = {
        import c.universe._

        x match {
            case q"true" => true
            case q"false" => false
            case _ => TypeError(c)("illegal argument", x, "Boolean literal")
        }
    }
}


private object AsInt {
    def apply(c: Context)(x: c.Tree): Int = {
        import c.universe._

        x match {
            case q"${y: Int}" => y
            case _ => TypeError(c)("illegal argument", x, "Int literal")
        }
    }
}


private object AsLong {
    def apply(c: Context)(x: c.Tree): Long = {
        import c.universe._

        x match {
            case q"${y: Long}" => y
            case _ => TypeError(c)("illegal argument", x, "Long literal")
        }
    }
}


private object AsString {
    def apply(c: Context)(x: c.Tree): String = {
        import c.universe._

        x match {
            case q"${y: String}" => y
            case _ => TypeError(c)("illegal argument", x, "String literal")
        }
    }
}


private object AsType {
    def apply(c: Context)(x: c.Tree): c.Type = {
        import c.universe._

        x match {
            case Literal(Constant(y: Type)) => y // q"${y: Type}" doesn't work.
            case _ => TypeError(c)("illegal argument", x, "Class literal")
        }
    }
}
