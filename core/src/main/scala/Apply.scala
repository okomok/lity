

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Apply {
    def apply(f: Any, a: Any): Any = macro ApplyImpl.apply
}


final class ApplyImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(f: c.Tree, a: c.Tree): c.Tree = {
        val es = TupleToList(c)(f).map { e =>
            TupleDealias(c)(e)
        }

        CollectFirst(es) {
            case q"${_}($x, ${y: String})" => betaReduce(x, y, a) match {
                case "" => None
                case y => Some(c.parse(y))
            }
            case e => c.abort(c.enclosingPosition, s"mapping entry error: ${show(e)}")
        } match {
            case Some(y) => y
            case None => c.abort(c.enclosingPosition, s"function application match error: ${show(a)}")
        }
    }

    private def betaReduce(x: c.Tree, y: String, a: c.Tree): String = {
        (TupleDealias(c)(x), TupleDealias(c)(a)) match {
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
}
