

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object Constantify {
    def apply(c: Context)(x: c.Tree): c.Tree = {
        import c.universe._

        def _apply(x: c.Tree): c.Tree = {
            c.typecheck(x) match {
                case y @ Literal(c.universe.Constant(_)) => y

                case q"$l == $r" => {
                    val y = _apply(l).equalsStructure(_apply(r))
                    q"$y"
                }

                case q"if($b) $t else $e" => {
                    if (AsBoolean(c)(b)) _apply(t) else _apply(e)
                }

                case q"if($b) $t" => {
                    if (AsBoolean(c)(b)) _apply(t) else q"()"
                }

                case _ => TypeError(c)("illegal argument", x, "constant-able expression")
            }
        }

        _apply(x)
    }
}
