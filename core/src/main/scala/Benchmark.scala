

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Benchmark {
    def apply(x: String): scala.Any = macro BenchmarkImpl.impl
}


final class BenchmarkImpl(override val c: Context) extends UntypedMacroImpl {
    import c.universe._

    override protected def macroImpl(x: c.Tree): c.Tree = {
        val start = System.currentTimeMillis
        c.typecheck(x)
        val elapsed = System.currentTimeMillis - start
        q"$elapsed"
    }
}
