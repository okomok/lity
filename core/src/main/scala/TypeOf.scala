

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object TypeOf {
    def apply(x: Any): Any = macro TypeOfImpl.apply
}


final class TypeOfImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(x: c.Tree): c.Tree = {
        q"${Here(c)}.Type.of($x)"
    }
}
