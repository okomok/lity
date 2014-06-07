

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


import scala.reflect.macros.ParseException


private object Tree {
    def replace(c: Context)(x: c.Tree, from: c.Tree, to: c.Tree): c.Tree = {
        import c.universe._
        // no typecheck for `1 + _I1` etc.
        val code = showCode(x).replace(showCode(from), showCode(to))
        try {
            c.parse(code)
        } catch {
            case e: ParseException => {
                c.abort(c.enclosingPosition, s"""
                |tree replacement failed: ${e.msg}
                |    ${show(x)}
                |        ${show(from)} --> ${show(to)}
                |    ${show(code)}
                """.stripMargin)
            }
        }
    }

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
