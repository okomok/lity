

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._


import junit.framework.Assert._



trait TypeClass[T] {
    val value: T
}

object TypeClass {
    implicit val ofInt: TypeClass[Int] = new TypeClass[Int] {
        override val value = 0
    }
}

class LocalImplicitTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial() {
        implicit val two: TypeClass[Int] = new TypeClass[Int] {
            override val value = 2
        }
        val i = implicitly[TypeClass[Int]]
        assertEquals(2, i.value)
    }
}

