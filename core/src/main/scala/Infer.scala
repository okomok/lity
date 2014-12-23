

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


// See: http://d.hatena.ne.jp/xuwei/20141222/1419224744
//      or shapeless/unions.scala


import scala.language.dynamics


object Infer extends Macro with Dynamic {
    def selectDynamic(x: String): Any = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._

        def apply(x: c.Tree): c.Tree = {
            import internal._
            import decorators._

            val y = c.typecheck(ParseTree(c)(x))
            val z = c.typecheck(tq"{ type apply = ${y.tpe.widen} }", mode = c.TYPEmode)

            Literal(Constant(())).setType(z.tpe)
        }
    }
}