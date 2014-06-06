

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._
import junit.framework.Assert._


class FunTest extends org.scalatest.junit.JUnit3Suite {

    def testLength1() {
        val y = Apply(F_(Length), (1, 2))
        assertEquals(2, y)
    }

    def testLength2() {
        object Lit {
            final val f = F_(Length)
        }
        val y = Apply(Lit.f, (1, 2))
        assertEquals(2, y)
    }

}
