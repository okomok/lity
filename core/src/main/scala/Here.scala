

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object Here {
    def apply(c: Context): c.Tree = {
        import c.universe._
        q"_root_.com.github.okomok.lity"
    }
}
