

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


import scala.language.implicitConversions


sealed trait Param


private object Param {
    def accepts(c: Context)(x: c.Tree, a: c.Tree): Boolean = {
        import c.universe._

        val xt = SafeTpe(c)(x)
        val at = SafeTpe(c)(a)

        if (xt <:< typeOf[AnyParam]) {
            true
        } else if (xt <:< typeOf[IntParam]) {
            at <:< typeOf[Int]
        } else if (xt <:< typeOf[LongParam]) {
            at <:< typeOf[Long]
        } else if (xt <:< typeOf[FloatParam]) {
            at <:< typeOf[Float]
        } else if (xt <:< typeOf[DoubleParam]) {
            at <:< typeOf[Double]
        } else if (xt <:< typeOf[BooleanParam]) {
            at <:< typeOf[Boolean]
        } else if (xt <:< typeOf[CharParam]) {
            at <:< typeOf[Char]
        } else if (xt <:< typeOf[StringParam]) {
            at <:< typeOf[String]
        } else {
            false
        }
    }

    def replace(c: Context)(s: String, x: c.Tree, a: c.Tree): String = {
        import c.universe._

        val x_ = showCode(x).reverse.take(3).reverse
        s.replace(x_, showCode(a))
    }
}


sealed trait AnyParam extends Param
sealed trait IntParam extends Param
sealed trait LongParam extends Param
sealed trait FloatParam extends Param
sealed trait DoubleParam extends Param
sealed trait BooleanParam extends Param
sealed trait CharParam extends Param
sealed trait StringParam extends Param


case object _I1 extends IntParam
case object _I2 extends IntParam
case object _I3 extends IntParam

case object _L1 extends LongParam
case object _L2 extends LongParam
case object _L3 extends LongParam

case object _F1 extends FloatParam
case object _F2 extends FloatParam
case object _F3 extends FloatParam

case object _D1 extends DoubleParam
case object _D2 extends DoubleParam
case object _D3 extends DoubleParam

case object _B1 extends BooleanParam
case object _B2 extends BooleanParam
case object _B3 extends BooleanParam

case object _C1 extends CharParam
case object _C2 extends CharParam
case object _C3 extends CharParam

case object _S1 extends StringParam
case object _S2 extends StringParam
case object _S3 extends StringParam

case object _X1 extends AnyParam
case object _X2 extends AnyParam
case object _X3 extends AnyParam
