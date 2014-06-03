

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class TupleTest extends org.scalatest.junit.JUnit3Suite {

    final val TUP1 = """(1, "h")"""
    val tup1: (Int, String) = P_(TUP1)

    def testGet() {
        val i: Int = Get(P_(TUP1), 0)
        assertEquals(i, 1)
    }

    object X
    object Y

    def testAppend() {
        val xs: (Int, String, X.type, Y.type) = Append((1, "h"), (X, Y))
        assertEquals(xs._2, "h")
    }

    def testAppendNil() {
        val xs: (Int, String) = Append((1, "h"), ())
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

    def testLength() {
        val n = Length((1,2,3))
        assertEquals(3, n)
    }

    def testFindConforms() {
        val x: Some[Int] = FindConforms[Int]((X, 2, Y))
        assertEquals(2, x.get)
    }

    def testMap() {
        val ys = Map((X, 2, Y), (
            (x: X.type) => Y
          , (x: Y.type) => X
          , (x: Int) => x + 1

        ))
        assertEquals((Y, 3, X), ys)
    }

    def testMap2() {

    }
}
