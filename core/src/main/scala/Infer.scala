

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


// Thanks to: macrogen


final class InferFunction1[T] {
    def apply[R](f: T => R): T => R = f
}

object InferFunction1 {
    def apply[T] = new InferFunction1[T]
}
