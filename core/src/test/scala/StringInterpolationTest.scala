

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class StringInterpolationTest extends org.scalatest.junit.JUnit3Suite {

    implicit class Plus(sc: StringContext) {
        object plus {
            def apply(x: Int, y: Int): Int = x + y
            def unapply(z: Int): Option[(Int, Int)] = Some((1, z-1))
        }
    }

    def testMe() {
        val (x, y) = (1, 2)
        assertEquals(3, plus"$x$y")

        val plus"$v$w" = 3
        assertEquals((1, 2), (v, w))
    }
}


