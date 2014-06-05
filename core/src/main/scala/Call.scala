

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


case object Otherwise


private object Call {
    def apply(c: Context)(f: c.Tree, a: c.Tree): c.Tree = {
        import c.universe._

        val es = Tuple.toList(c)(f).map { e =>
            normalizePair(c)(e)
        }

        CollectFirst(es) {
            case q"${_}($x, $y)" => betaReduce(c)(x, y, a)
        } match {
            case Some(y) => y
            case None => c.abort(c.enclosingPosition, s"illegal argument: ${show(a)}")
        }
    }

    private def normalizePair(c: Context)(e: c.Tree): c.Tree = {
        import c.universe._
        e match {
            case q"${_}($x).->[${_}]($y)" => q"($x, $y)"
            case _ => e
        }
    }

    private def betaReduce(c: Context)(x: c.Tree, y: c.Tree, a: c.Tree): Option[c.Tree] = {
        import c.universe._
        _betaReduce(c)(x, y, a) match {
            case EmptyTree => None
            case y_ => Some(y_)
        }
    }

    private def _betaReduce(c: Context)(x: c.Tree, y: c.Tree, a: c.Tree): c.Tree = {
        import c.universe._

        (normalizePair(c)(x), a) match {
            case (x_, a) if Param.accepts(c)(x, a) => TreeReplace(c)(y, x, a)
            case (q"${_}(..$xs)", q"${_}(..$as)") if xs.length == as.length => {
                (xs, as).zipped.foldLeft(y) { case (y_, (x_, a_)) =>
                    _betaReduce(c)(x_, y_, a_)
                }
            }
            case (x_, a) if x_.equalsStructure(a) => y
            case _ => EmptyTree
        }
    }
}
