

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._

class ConstantStringTest extends org.scalatest.junit.JUnit3Suite {

    final val x = "hello"
    final val y = ls"$x world"

    Assert { isConstant(y) }
    Assert { equal("hello world", y) }

    def testTrivial() {
        assertEquals("hello world", y)
    }

    def testLength0() {
        val y = apply(Fun(X1 -> ls"Tuple.length(X1)"), (1, 2, 3))
        assertEquals(3, y)
    }

}
