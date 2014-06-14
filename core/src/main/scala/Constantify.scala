

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object Constantify {
    // Arithmetic operations etc supported.
    def builtin(c: Context)(x: c.Tree): c.Tree = {
        c.typecheck(x)
    }

    def apply(c: Context)(x: c.Tree): c.Tree = {
        import c.universe._

        def _apply(x: c.Tree): c.Tree = {
            builtin(c)(x) match {
                case Literal(c.universe.Constant(a: Type)) => {
                    Literal(Constant(a.dealias))
                }

                case y @ Literal(c.universe.Constant(_)) => y

                case q"!$b" => {
                    val y = !(AsBoolean(c)(_apply(b)))
                    q"$y"
                }

                case q"$l == $r" => {
                    val y = _apply(l).equalsStructure(_apply(r))
                    q"$y"
                }

                case q"$l != $r" => {
                    val y = !(_apply(l).equalsStructure(_apply(r)))
                    q"$y"
                }

                case q"if($b) $t else $e" => {
                    if (AsBoolean(c)(_apply(b))) _apply(t) else _apply(e)
                }
/* Unit is not literal.
                case q"if($b) $t" => {
                    if (AsBoolean(c)(_apply(b))) _apply(t) else q"()"
                }
*/
                case _ => TypeError(c)("illegal argument", x, "constant-able expression")
            }
        }

        _apply(x)
    }
}
