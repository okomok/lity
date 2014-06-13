

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object ScalaVersion {

    def apply(): Long = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._

        def apply(): c.Tree = {
            val y = Version.encode(util.Properties.versionString)
            q"$y"
        }
    }
}


object ScalaVersionString {
    def string(): String = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._

        def apply(): c.Tree = {
            val y = util.Properties.versionString
            q"$y"
        }
    }
}
