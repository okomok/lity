

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class PolyFunTest extends org.scalatest.junit.JUnit3Suite {

    final val Fun1 = Fun(
        (I1, I2) -> "I1 + I2", X1 -> "X1"
    )

    def testTrivial1() {
        val ys = Tuple.map( ((1, 2), "h"), Fun1 )
        assertEquals(( 3, "h" ), ys)
    }

    def testTrivial2() {
        val ys = Tuple.map( ((1, 'a'), "h"), Fun1 )
        assertEquals( ((1, 'a'), "h"), ys)
    }

    final val Fun2 = Fun(
        (I1, (I2, I3)) -> "I1 + I2 + I3", S1 -> "S1"
    )

    def testTrivial3() {
        val ys = Tuple.map( ((1, (2,3)), "h"), Fun2 )
        assertEquals( (6, "h"), ys)
    }

    final val Fun3 = Fun(
        (I1, (I2, I3)) -> "I1 + I2 + I3", S1 -> "S1"
    )

    final val FunNested = Fun(
        (I1, 3) -> "apply(Fun1, (I1, I1))"
    )

    def testNested1() {
        val y = apply(FunNested, (4, 3))
        assertEquals(8, y)
    }

    final val Inc = Fun(
        X1 -> "X1 + 1"
    )

    def testInc() {
        val y = apply(Inc, 3)
        assertEquals(4, y)
        val z = apply(Inc, "h")
        assertEquals("h1", z)
    }

    final val FunLength = Fun( X1 -> "Tuple.length(X1)" )

    def testLength() {
        val y = apply(FunLength, (1, 2))
        assertEquals(2, y)
    }

    final val FunToTuple = Fun( X1 -> "Tuple.length(Tuple.fromString(X1))" )

    def testToTuple() {
        val y = apply(FunToTuple, "12")
        assertEquals(2, y)
    }

    def testIncDefer() {
        object f {
            final val value = Fun( X1 -> "1 + X1" )
        }

        val z = apply(f.value, "h")
        assertEquals("1h", z)
    }

}
