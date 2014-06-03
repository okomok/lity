

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object P_ {
    def apply(x: String): Any = macro Parse.impl
}

object Parse {
    def apply(x: String): Any = macro Parse.impl
}


final class Parse(override val c: Context) extends InContext {
    def impl(x: c.Tree): c.Tree = _Parse(c)(x)
}


private object _Parse {
    def apply(c: Context)(x: c.Tree): c.Tree = {
        val code = ExtractString(c)(x)
        c.parse(code)
    }
}

