

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


import scala.reflect.macros.ParseException


private object RequireExpr {
    def apply(c: Context)(x: c.Tree): Unit = try {
        ParseExpr(c)(x)
    } catch {
        case _: ParseException => {
            TypeError(c)("illegal argument", x, "scala expression")
        }
    }
}
