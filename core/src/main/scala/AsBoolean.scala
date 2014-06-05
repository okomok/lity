

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object AsBoolean {
    def apply(c: Context)(x: c.Tree): Boolean = {
        import c.universe._

        x match {
            case q"true" => true
            case q"false" => false
        }
    }
}
