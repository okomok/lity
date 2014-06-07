

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Benchmark {
    def apply(x: String): scala.Any = macro BenchmarkImpl.apply
}


final class BenchmarkImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply(x: c.Tree): c.Tree = {
        val y = _Parse(c)(x)
        val start = System.currentTimeMillis
        c.typecheck(y)
        val elapsed = System.currentTimeMillis - start
        q"$elapsed"
    }
}
