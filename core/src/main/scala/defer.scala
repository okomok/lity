

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


// Defers macro-application in macro arguments.


class Deferred(x: Any) {
    def apply(as: Any*): Any = ???
}


object Deferred {
    def apply(x: Any): Deferred = new Deferred(x)
}


object defer {
    def apply(x: Any): Deferred = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._
        def apply(x: c.Tree): c.Tree = q"${Here(c)}.Deferred($x)"
    }
}


object Undefer {
    def apply(c: Context)(x: c.Tree): c.Tree = c.typecheck {
        import c.universe._

        new Transformer {
            override def transform(x: c.Tree): c.Tree = {
                x match {
                    case q"com.github.okomok.lity.Deferred.apply($y)" => y
                    case x => super.transform(x)
                }
            }
        }.transform {
            Duplicate(c)(x)
        }
    }
}
