

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


trait ReturnConstant0 extends MacroImpl0 {
    abstract override def apply(): c.Tree = EnsuringConstant(c) {
        super.apply()
    }
}


trait ReturnConstant1 extends MacroImpl1 {
    abstract override def apply(x: c.Tree): c.Tree = EnsuringConstant(c) {
        super.apply(x)
    }
}


trait ReturnConstant2 extends MacroImpl2 {
    abstract override def apply(x: c.Tree, y: c.Tree): c.Tree = EnsuringConstant(c) {
        super.apply(x, y)
    }
}
