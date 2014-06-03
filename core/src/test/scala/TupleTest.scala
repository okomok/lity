

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class TupleTest extends org.scalatest.junit.JUnit3Suite {

    final val TUP1 = """(1, "h")"""
    val tup1: (Int, String) = C_(TUP1)

    def testNth() {
        val i: Int = Nth(C_(TUP1), 0)
        assertEquals(i, 1)
    }

    def testAppend() {
        object X
        object Y
        val xs: (Int, String, X.type, Y.type) = Append((1, "h"), (X, Y))
        assertEquals(xs._2, "h")
    }

    def testHead() {
        val i: Int = Head((1, "h"))
        assertEquals(i, 1)
    }

    def testTail() {
        val ys: (String, Char) = Tail((1, "h", 'a'))
        assertEquals('a', ys._2)
    }
}
