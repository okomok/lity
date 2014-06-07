

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Def extends Tuple


object Def_ {
    def apply(arg: Any*): Any = macro Def_Impl.apply
}


final class Def_Impl(override val c: Context) extends InContext {
    import c.universe._

    def apply(arg: c.Tree*): c.Tree = {
        q"${Here(c)}.L_ { ${Here(c)}.Def(..$arg) }"
    }
}
