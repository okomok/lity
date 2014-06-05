

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object TreeReplace {
    def apply(c: Context)(x: c.Tree, from: c.Tree, to: c.Tree): c.Tree = {
        import c.universe._
        c.parse(showCode(x).replace(showCode(from), showCode(to)))
    }
}


private object TreeToOption {
    def apply(c: Context)(x: c.Tree): Option[c.Tree] = {
        import c.universe._

        x match {
            case EmptyTree => None
            case _ => Some(x)
        }
    }
}
