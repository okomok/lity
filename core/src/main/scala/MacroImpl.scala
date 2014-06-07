

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


trait MacroImpl1 extends InContext {
    import c.universe._

    protected def name: c.Tree
    protected def impl(arg: c.Tree): c.Tree

    final def apply(arg: c.Tree): c.Tree = {
        if (Tree.hasParam(c)(arg)) {
            q"${Here(c)}.Defer(${name})($arg)"
        } else {
            impl(arg)
        }
    }
}


trait MacroImpl2 extends InContext {
    import c.universe._

    protected def name: c.Tree
    protected def impl(arg1: c.Tree, arg2: c.Tree): c.Tree

    final def apply(arg1: c.Tree, arg2: c.Tree): c.Tree = {
        if (Tree.hasParam(c)(q"($arg1, $arg2)")) {
            q"${Here(c)}.Defer(${name})($arg1, $arg2)"
        } else {
            impl(arg1, arg2)
        }
    }
}


trait MacroImpl3 extends InContext {
    import c.universe._

    protected def name: c.Tree
    protected def impl(arg1: c.Tree, arg2: c.Tree, arg3: c.Tree): c.Tree

    final def apply(arg1: c.Tree, arg2: c.Tree, arg3: c.Tree): c.Tree = {
        if (Tree.hasParam(c)(q"($arg1, $arg2, $arg3)")) {
            q"${Here(c)}.Defer(${name})($arg1, $arg2, $arg3)"
        } else {
            impl(arg1, arg2, arg3)
        }
    }
}
