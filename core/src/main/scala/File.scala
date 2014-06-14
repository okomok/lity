

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object FileName extends Macro {
    def apply(): String = macro Impl.apply

    final class Impl(override val c: Context) extends MacroImpl0 {
        import c.universe._

        override protected def impl(): c.Tree = EnsuringConstant(c) {
            val y = c.macroApplication.pos.source.toString
            q"$y"
        }
    }
}


/*
Full-path is a privacy.
object File {
    def apply(): String = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._

        def apply(): c.Tree = {
            val y = c.macroApplication.pos.source.file.toString
            q"$y"
        }
    }
}
*/
