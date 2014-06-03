

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object TypeOf {
    def apply(x: Any): Any = macro TypeOfImpl.impl
}


final class TypeOfImpl(override val c: Context) extends InContext {
    import c.universe._

    def impl(x: c.Tree): c.Tree = TypeTree(x.tpe)
}
