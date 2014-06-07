

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


// Makes expressions well-typed.
trait Bottom {
    final def value: Bottom = new Bottom{}

    final def apply(x1: Any): Bottom = new Bottom{}
    final def apply(x1: Any, x2: Any): Bottom = new Bottom{}
    final def apply(x1: Any, x2: Any, x3: Any): Bottom = new Bottom{}

    final def <(x: Any): Bottom = new Bottom{}
    final def >(x: Any): Bottom = new Bottom{}
    final def <=(x: Any): Bottom = new Bottom{}
    final def >=(x: Any): Bottom = new Bottom{}

    final def +(x: Any): Bottom = new Bottom{}
    final def -(x: Any): Bottom = new Bottom{}
    final def *(x: Any): Bottom = new Bottom{}
    final def /(x: Any): Bottom = new Bottom{}
    final def %(x: Any): Bottom = new Bottom{}

    final def &(x: Any): Bottom = new Bottom{}
    final def |(x: Any): Bottom = new Bottom{}
    final def ^(x: Any): Bottom = new Bottom{}

    final def <<(x: Any): Bottom = new Bottom{}
    final def >>(x: Any): Bottom = new Bottom{}
    final def >>>(x: Any): Bottom = new Bottom{}

    final def unary_+ : Bottom = new Bottom{}
    final def unary_- : Bottom = new Bottom{}
    final def unary_~ : Bottom = new Bottom{}

    final def &&(x: Any): Bottom = new Bottom{}
    final def ||(x: Any): Bottom = new Bottom{}

    final def unary_! : Bottom = new Bottom{}
}
