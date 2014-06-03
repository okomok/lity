

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object L_ {
    def apply(x: Any): String = macro Literalize.impl
}

object Literalize {
    def apply(x: Any): String = macro Literalize.impl
}


final class Literalize(override val c: Context) extends InContext {
    import c.universe._

    def impl(x: c.Tree): c.Tree = {
        val y = showCode(x)
        q"$y"
    }
}