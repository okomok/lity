

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


// Partial fix of SI-8650.

final class StringInterpolatorImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(args: c.Tree*): c.Tree = {
        val parts = c.prefix.tree match {
            case q"${_}(${_}(..$as))" => toStringList(as)
        }
        val vals = toStringList(args.toList)
        val y = new StringContext(parts: _*).s(vals: _*)
        q"$y"
    }

    private def toStringList(xs: List[c.Tree]): List[String] = {
        xs.map {
            case q"${s: String}" => s
            case a => TypeError(c)("illegal argument", a, "String literal")
        }
    }
}
