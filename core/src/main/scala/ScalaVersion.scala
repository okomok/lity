

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object ScalaVersion extends Macro {
    def apply(): Long = macro Impl.apply

    final class Impl(override val c: Context) extends MacroImpl0 {
        import c.universe._

        override protected def impl(): c.Tree = EnsuringConstant(c) {
            val y = Version.encode(scala.util.Properties.versionString)
            q"$y"
        }
    }
}


object ScalaVersionString extends Macro {
    def apply(): String = macro Impl.apply

    final class Impl(override val c: Context) extends MacroImpl0 {
        import c.universe._

        override protected def impl(): c.Tree = EnsuringConstant(c) {
            val y = scala.util.Properties.versionString
            q"$y"
        }
    }
}
