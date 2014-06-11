

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object apply {
    def apply(f: Any, a: Any): Any = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._

        def apply(f: c.Tree, a: c.Tree): c.Tree = {
            val es = Tuple.treeToList(c)(f).map { e =>
                Tuple.treeDealias(c)(e)
            }

            CollectFirst(es) {
                case q"${_}($x, ${y: String})" => betaReduce(x, y, a) match {
                    case "" => None
                    case y => Some(c.parse(y))
                }
                case e => TypeError(c)("illegal mapping entry", e, "(_, String)")
            } match {
                case Some(y) => y
                case None => TypeError(c)("illegal argument", a, paramTypes(es))
            }
        }

        private def betaReduce(x: c.Tree, y: String, a: c.Tree): String = {
            (Tuple.treeDealias(c)(x), Tuple.treeDealias(c)(a)) match {
                case (x, a) if Param.accepts(c)(x, a) => {
                    Param.replace(c)(y, x, a)
                }
                case (q"${_}(..$xs)", q"${_}(..$as)") if xs.length == as.length => {
                    (xs, as).zipped.foldLeft(y) { case (y, (x, a)) =>
                        betaReduce(x, y, a)
                    }
                }
                case (x, a) if x.equalsStructure(a) => y
                case _ => ""
            }
        }

        private def paramTypes(es: List[c.Tree]): String = {
            es.map {
                case q"${_}($x, ${_})" => x
            }.mkString(" or ")
        }
    }
}
