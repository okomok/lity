

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


trait ConstantParam1 extends MacroImpl1 {
    abstract override def apply(x: c.Tree): c.Tree = {
        super.apply(Constantify(c)(x))
    }
}


trait ConstantParam2 extends MacroImpl2 {
    abstract override def apply(x: c.Tree, y: c.Tree): c.Tree = {
        super.apply(Constantify(c)(x), Constantify(c)(y))
    }
}
