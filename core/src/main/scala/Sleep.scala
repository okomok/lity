

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Sleep {
    def apply(x: Long): scala.Unit = macro SleepImpl.impl
}


final class SleepImpl(override val c: Context) extends InContext {
    import c.universe._

    def impl(x: c.Tree): c.Tree = {
        Thread.sleep(extractLong(x))
        q"()"
    }

    private def extractLong(x: c.Tree): Long = {
        x match {
            case Literal(Constant(ms: Long)) => ms
            case t => CompileError.illegalArgument(c)(show(t) + " is required to be Long literal.")
        }
    }
}
