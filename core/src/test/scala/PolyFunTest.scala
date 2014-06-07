

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class PolyFunTest extends org.scalatest.junit.JUnit3Suite {

    final val Fun1 = Def_(
        (_I1, _I2) -> (_I1 + _I2), _X1 -> _X1
    )

    def testTrivial1() {
        val ys = Map( ((1, 2), "h"), Fun1 )
        assertEquals(( 3, "h" ), ys)
    }

    def testTrivial2() {
        val ys = Map( ((1, 'a'), "h"), Fun1 )
        assertEquals( ((1, 'a'), "h"), ys)
    }

    final val Fun2 = Def_(
        (_I1, (_I2, _I3)) -> (_I1 + _I2 + _I3), _S1 -> _S1
    )

    def testTrivial3() {
        val ys = Map( ((1, (2,3)), "h"), Fun2 )
        assertEquals( (6, "h"), ys)
    }

    final val Fun3 = Def_(
        (_I1, (_I2, _I3)) -> (_I1 + _I2 + _I3), _S1 -> _S1
    )

    final val FunNested = Def_(
        (_I1, 3) -> Apply(Fun1, (_I1, _I1)),
        (), // accepted
        31  //
    )

    def testNested1() {
        val y = Apply(FunNested, (4, 3))
        assertEquals(8, y)
    }

    final val Inc = Def_(
        _X1 -> (_X1 + 1), ()
    )

    def testInc() {
        val y = Apply(Inc, 3)
        assertEquals(4, y)
        val z = Apply(Inc, "h")
        assertEquals("h1", z)
    }

    final val FunLength = Def_( _X1 -> Length(_X1) )

    def testLength() {
        val y = Apply(FunLength, (1, 2))
        assertEquals(2, y)
    }

    final val FunToTuple = Def_( _X1 -> Length(ToTuple(_X1)) )

    def testToTuple() {
        val y = Apply(FunToTuple, "12")
        assertEquals(2, y)
    }

    def testIncDefer() {
        object Lit {
            final val Fun = Def_( _X1 -> (Defer(1) + _X1) )
        }

        val z = Apply(Lit.Fun, "h")
        assertEquals("1h", z)
    }

}
