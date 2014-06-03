

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object ParamType {
    def apply(c: Context)(f: c.Tree): c.Tree = {
        import c.universe._
        val TypeRef(_, _, List(t, _)) = f.tpe.dealias
        TypeTree(t)
    }
}
