

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class RepeatTest extends org.scalatest.junit.JUnit3Suite {

    object Inc {
        def apply(i: Int) = i + 1
    }

    def testRepeat0 {
        assertEquals(5, Repeat(Inc, 0, 5))
    }

    def testRepeatMethod0 {
        val xs = List(0,1,2,3)
        assertEquals(0, RepeatMethod(xs.tail, 0).head)
    }

    def testRepeat {
        assertEquals(4, Repeat(Inc, 3, 1))
    }

    def testRepeatMethod {
        val xs = List(0,1,2,3)
        assertEquals(3, RepeatMethod(xs.tail, 3).head)
    }

    def testRepeatLambda {
        assertEquals(5, Repeat((_: Int) + 1, 0, 5))
    }
}
