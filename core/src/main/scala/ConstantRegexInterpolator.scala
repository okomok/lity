

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


final class StaticRegexInterpolatorImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(args: c.Tree*): c.Tree = EnsuringConstant(c) {
        val parts = c.prefix.tree match {
            case q"${_}(${_}(..$as))" => toStringList(as)
        }
        val vals = toStringList(args.toList)

//      if (constantForall(args.toList)) {
            val y = new StringContext(parts: _*).s(vals: _*)
            q"$y"
//      } else {
//          q"new StringContext(..$parts).s(..$vals)"
//      }
    }

    private def toStringList(xs: List[c.Tree]): List[String] = {
        xs.map {
            case q"${s: String}" => s
            case a => TypeError(c)("illegal argument", a, "String literal")
        }
    }

    private def constantForall(args: List[c.Tree]): Boolean = {
        args.forall {
            case q"${s: String}" => true
            case a => false
        }
    }
}
