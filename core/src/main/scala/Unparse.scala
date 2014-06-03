

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object U_ {
    def apply(x: Any): String = macro Unparse.impl
}

object Unparse {
    def apply(x: Any): String = macro Unparse.impl
}


final class Unparse(override val c: Context) extends InContext {
    import c.universe._

    def impl(x: c.Tree): c.Tree = {
        val y = showCode(x)
        q"$y"
    }
}
