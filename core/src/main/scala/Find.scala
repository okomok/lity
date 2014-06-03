

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object FindConforms {
    def apply[To](tup: Any): Any = macro FindConformsImpl.impl[To]
}


final class FindConformsImpl(override val c: Context) extends InContext {
    import c.universe._

    def impl[To](tup: c.Tree)(implicit To: c.WeakTypeTag[To]): c.Tree = {
        val xs = Tuple.toList(c)(tup)
        val y = xs.find { x =>
            x.tpe <:< To.tpe
        }
        q"$y"
    }
}
