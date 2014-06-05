

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


trait MacroImpl extends InContext {
    import c.universe._

    protected def name: c.Tree
    protected def impl(arg: c.Tree): c.Tree

    final def apply(arg: c.Tree): c.Tree = {
        if (TreeHasParam(c)(arg)) {
            q"${Here(c)}.Defer(${name})($arg)"
        } else {
            impl(arg)
        }
    }
}
