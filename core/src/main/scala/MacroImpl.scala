

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


trait MacroImpl0 extends InContext {
    protected def impl(): c.Tree
    def apply(): c.Tree = impl()
}


trait MacroImpl1 extends InContext {
    protected def impl(x: c.Tree): c.Tree
    def apply(x: c.Tree): c.Tree = impl(x)
}


trait MacroImpl2 extends InContext {
    protected def impl(x: c.Tree, y: c.Tree): c.Tree
    def apply(x: c.Tree, y: c.Tree): c.Tree = impl(x, y)
}
