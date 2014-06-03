

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


trait UntypedMacroImpl extends InContext {
    protected def macroImpl(x: c.Tree): c.Tree

    final def impl(x: c.Tree): c.Tree = {
        val code = ExtractString(c)(x)
        macroImpl(c.parse(code))
    }
}
