

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object TupleFromList {
    def apply(c: Context)(xs: List[c.Tree]): c.Tree = {
        import c.universe._

        if (xs.isEmpty) {
            q"()"
        } else {
            val tn = TermName(s"Tuple${xs.length}")
            q"scala.$tn(..$xs)"
        }
    }
}
