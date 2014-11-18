

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


// Repeat(f, 3, x) --> f(f(f(x))
object Repeat extends Macro {
    def apply(f: Any, n: Int, x: Any): Any = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._

        def apply(f: c.Tree, n: c.Tree, x: c.Tree): c.Tree = {
            (0 until AsInt(c)(n)).foldRight(x) { (_, x) => q"$f($x)" }
        }
    }
}


// RepeatMethod(x.f, 3) --> x.f.f.f
object RepeatMethod extends Macro {
    def apply(xf: Any, n: Int): Any = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._

        def apply(xf: c.Tree, n: c.Tree): c.Tree = {
            def oneMore(xf: c.Tree): c.Tree = {
                xf match {
                    case q"$x.$f" => q"$xf.$f"
                    case q"$x.$f(..$as)" => q"$xf.$f(..$as)"
                }
            }

            def zero(xf: c.Tree): c.Tree = {
                xf match {
                    case q"$x.$f" => q"$x"
                    case q"$x.$f(..$as)" => q"$x"
                }
            }

            val i = AsInt(c)(n)
            require(i >= 0)

            if (i == 0) {
                zero(xf)
            } else {
                (0 until (i-1)).foldRight(xf) { (_, xf) => oneMore(xf) }
            }
        }
    }
}
