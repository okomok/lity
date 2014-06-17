

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object Constantify {
    def apply(c: Context)(x: c.Tree): c.Tree = {
        import c.universe._

        def _apply(x: c.Tree): c.Tree = {
            builtin(c)(x) match {

                case Literal(Constant(a: Type)) => { // q"${a: Type}" doesn't work.
                    Literal(Constant(a.dealias)) // dealias for type equality
                }

                case y @ Literal(Constant(_)) => y

                case y @ q"$l == $r" => {
                    (_apply(l), _apply(r)) match {
                        case (Literal(Constant(l_)), Literal(Constant(r_))) => q"${l_ == r_}"
                        case _ => y
                    }
                }

                case y @ q"$l != $r" => {
                    (_apply(l), _apply(r)) match {
                        case (Literal(Constant(l_)), Literal(Constant(r_))) => q"${l_ != r_}"
                        case _ => y
                    }
                }

                case q"!$b" => {
                    val b_ = AsBoolean(c)(_apply(b))
                    q"${!b_}"
                }

                case q"if ($b) $t else $e" => {
                    if (AsBoolean(c)(_apply(b))) _apply(t) else _apply(e)
                }

                case q"$l.$m" => {
                    val l_ = _apply(l)
                    builtin(c)(q"$l_.$m")
                }

                case q"$l.$m(..$rs)" => {
                    val l_ = _apply(l)
                    val rs_ = rs.map { r => _apply(r) }
                    builtin(c)(q"$l_.$m(..$rs_)")
                }

                case y => y
            }
        }

        val y = _apply(x)
        if (IsConstantTree(c)(y)) {
            y
        } else {
            TypeError(c)("illegal argument", x, "constant-able expression")
        }
    }

    // Arithmetic operations etc supported.
    private def builtin(c: Context)(x: c.Tree): c.Tree = {
        c.typecheck(x)
    }
}
