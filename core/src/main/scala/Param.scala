

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


sealed trait Param {
    final def <(x: Any): Param = new Param{}
    final def >(x: Any): Param = new Param{}
    final def <=(x: Any): Param = new Param{}
    final def >=(x: Any): Param = new Param{}

    final def +(x: Any): Param = new Param{}
    final def -(x: Any): Param = new Param{}
    final def *(x: Any): Param = new Param{}
    final def /(x: Any): Param = new Param{}
    final def %(x: Any): Param = new Param{}

    final def &(x: Any): Param = new Param{}
    final def |(x: Any): Param = new Param{}
    final def ^(x: Any): Param = new Param{}

    final def <<(x: Any): Param = new Param{}
    final def >>(x: Any): Param = new Param{}
    final def >>>(x: Any): Param = new Param{}

    final def unary_+ : Param = new Param{}
    final def unary_- : Param = new Param{}
    final def unary_~ : Param = new Param{}

    final def &&(x: Any): Param = new Param{}
    final def ||(x: Any): Param = new Param{}

    final def unary_! : Param = new Param{}
}


object Param {
    def accepts(c: Context)(x: c.Tree, a: c.Tree): Boolean = {
        import c.universe._

        val (xt, at) = (x.tpe, a.tpe)

        if (xt <:< typeOf[AnyParam]) {
            true
        } else if (xt <:< typeOf[IntParam]) {
            at <:< typeOf[Int] || at <:< typeOf[IntParam]
        } else if (xt <:< typeOf[StringParam]) {
            at <:< typeOf[String] || at <:< typeOf[StringParam]
        } else {
            false
        }
    }
}


sealed trait AnyParam extends Param
sealed trait IntParam extends Param
sealed trait StringParam extends Param


case object _I1 extends IntParam
case object _I2 extends IntParam
case object _I3 extends IntParam

case object _S1 extends StringParam
case object _S2 extends StringParam
case object _S3 extends StringParam

case object _X1 extends AnyParam
case object _X2 extends AnyParam
case object _X3 extends AnyParam
