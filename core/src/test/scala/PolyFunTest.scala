

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class PolyFunTest extends org.scalatest.junit.JUnit3Suite {

    final val Fun1 = L_{
        ( (_I1, _I2) -> (_I1 + _I2), _X1 -> _X1 )
    }

    def testTrivial1() {
        val ys = Map( ((1, 2), "h"), Fun1 )
        assertEquals(( 3, "h" ), ys)
    }

    def testTrivial2() {
        val ys = Map( ((1, 'a'), "h"), Fun1 )
        assertEquals( ((1, 'a'), "h"), ys)
    }

    final val Fun2 = L_{
        ( (_I1, (_I2, _I3)) -> (_I1 + _I2 + _I3), _S1 -> _S1 )
    }

    def testTrivial3() {
        val ys = Map( ((1, (2,3)), "h"), Fun2 )
        assertEquals( (6, "h"), ys)
    }

}
