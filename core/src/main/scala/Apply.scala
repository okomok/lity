

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Apply {
    def apply(f: Any, x: Any): Any = macro ApplyImpl.apply
}


final class ApplyImpl(override val c: Context) extends InContext {
    import c.universe._
    def apply(f: c.Tree, x: c.Tree): c.Tree = Apply_(c)(f, x)
}


private object Apply_ {
    def apply(c: Context)(f: c.Tree, a: c.Tree): c.Tree = {
        import c.universe._

        val es = Tuple.toList(c)(f).map { e =>
            Tuple.normalize(c)(e)
        }

        CollectFirst(es) {
            case q"${_}($x, ${y: String})" => Tree.toOption(c) {
                betaReduce(c)(x, c.parse(y), a)
            }
            case e => c.abort(c.enclosingPosition, s"mapping entry error: ${show(e)}")
        } match {
            case Some(y) => y
            case None => c.abort(c.enclosingPosition, s"function application match error: ${show(a)}")
        }
    }

    private def betaReduce(c: Context)(x: c.Tree, y: c.Tree, a: c.Tree): c.Tree = {
        import c.universe._

        (Tuple.normalize(c)(x), Tuple.normalize(c)(a)) match {
            case (x, a) if Param.accepts(c)(x, a) => {
                bindArg(c)(x, y, a)
            }
            case (q"${_}(..$xs)", q"${_}(..$as)") if xs.length == as.length => {
                (xs, as).zipped.foldLeft(y) { case (y, (x, a)) =>
                    betaReduce(c)(x, y, a)
                }
            }
            case (x, a) if x.equalsStructure(a) => y
            case _ => EmptyTree
        }
    }

    private def bindArg(c: Context)(x: c.Tree, y: c.Tree, a: c.Tree): c.Tree = {
        import c.universe._

        new Transformer {
            override def transform(z: c.Tree): c.Tree = {
                z match {
                    case z if Param.equalsIdent(c)(x, z) => a
                    case q"${s: String}" => Param.replace(c)(s, x, a)
                    case _ => super.transform(z)
                }
            }
        }.transform(y)
    }
}
