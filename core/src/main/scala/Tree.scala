

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


import scala.reflect.macros.ParseException


private object Tree {
    def toOption(c: Context)(x: c.Tree): Option[c.Tree] = {
        import c.universe._

        x match {
            case EmptyTree => None
            case _ => Some(x)
        }
    }

    def hasParam(c: Context)(x: c.Tree): Boolean = {
        import c.universe._
        showCode(x).matches(s".*$here\\._[A-Z]\\d.*")
    }
}
