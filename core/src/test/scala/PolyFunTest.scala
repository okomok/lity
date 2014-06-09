

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class PolyFunTest extends org.scalatest.junit.JUnit3Suite {

    final val Fun1 = Fun(
        (_I1, _I2) -> "_I1 + _I2", _X1 -> "_X1"
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
        (_I1, (_I2, _I3)) -> "_I1 + _I2 + _I3", _S1 -> "_S1"
    )

    def testTrivial3() {
        val ys = Tuple.map( ((1, (2,3)), "h"), Fun2 )
        assertEquals( (6, "h"), ys)
    }

    final val Fun3 = Fun(
        (_I1, (_I2, _I3)) -> "_I1 + _I2 + _I3", _S1 -> "_S1"
    )

    final val FunNested = Fun(
        (_I1, 3) -> "Apply(Fun1, (_I1, _I1))"
    )

    def testNested1() {
        val y = Apply(FunNested, (4, 3))
        assertEquals(8, y)
    }

    final val Inc = Fun(
        _X1 -> "_X1 + 1"
    )

    def testInc() {
        val y = Apply(Inc, 3)
        assertEquals(4, y)
        val z = Apply(Inc, "h")
        assertEquals("h1", z)
    }

    final val FunLength = Fun( _X1 -> "Tuple.length(_X1)" )

    def testLength() {
        val y = Apply(FunLength, (1, 2))
        assertEquals(2, y)
    }

    final val FunToTuple = Fun( _X1 -> "Tuple.length(Tuple.fromString(_X1))" )

    def testToTuple() {
        val y = Apply(FunToTuple, "12")
        assertEquals(2, y)
    }

    def testIncDefer() {
        object Lit {
            final val ToFun = Fun( _X1 -> "1 + _X1" )
        }

        val z = Apply(Lit.ToFun, "h")
        assertEquals("1h", z)
    }

}
