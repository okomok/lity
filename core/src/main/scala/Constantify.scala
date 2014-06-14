

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object Constantify {
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

                case y => y
            }
        }

        _apply(x) match {
            case Literal(Constant(_: Unit)) => illegal(c)(x) // Unit is not literal.
            case y @ Literal(Constant(_)) => y
            case _ => illegal(c)(x)
        }
    }

    // Arithmetic operations etc supported.
    private def builtin(c: Context)(x: c.Tree): c.Tree = {
        c.typecheck(x)
    }

    private def illegal(c: Context)(x: c.Tree): Nothing = {
        TypeError(c)("illegal argument", x, "constant-able expression")
    }
}
