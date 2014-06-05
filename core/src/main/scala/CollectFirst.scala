

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


private object CollectFirst {
    def apply[A, B](xs: List[A])(f: A => Option[B]): Option[B] = {
        xs match {
            case x :: xs => {
                f(x) match {
                    case None => apply(xs)(f)
                    case y => y
                }
            }
            case Nil => None
        }
    }
}
