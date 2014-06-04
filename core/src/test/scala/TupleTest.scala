

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class TupleTest extends org.scalatest.junit.JUnit3Suite {

    final val TUP1 = L_{ (1, "h") }

    def testParse() {
        val tup1: (Int, String) = Parse(TUP1)
        ()
    }

    def testGet() {
        val i: Int = Get(TUP1, 0)
        assertEquals(i, 1)
    }

    object X
    object Y

    def testFlatten0() {
        val xs: (Int, String) = Flatten(TUP1, ())
        assertEquals(xs._2, "h")
    }

    def testFlatten2() {
        val ys = Flatten((1, "h"), (X, Y))
        assertEquals((1, "h", X, Y), ys)
    }

    def testAppend() {
        val ys = Append((1, "h"), X)
        assertEquals((1, "h", X), ys)
    }

    def testPrepend() {
        val ys = Prepend((1, "h"), X)
        assertEquals((X, 1, "h"), ys)
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

    final val PolyFun = L_ { (
        (x: X.type) => Y
      , (x: Y.type) => X
      , (x: Int) => x + 1
    ) }

    final val YS = L_ { Map((X, 2, Y), PolyFun) }

    def testLiteralize() {
        val zs = Map(YS, (
            (x: X.type) => Y
          , (x: Y.type) => X
          , (x: Int) => x + 1

        ))

        assertEquals((X, 4, Y), zs)
    }

    def testReverse() {
        val ys = Reverse((1, "h", 'a'))
        assertEquals(('a', "h", 1), ys)
    }

    def testFilter() {
        val ys = Filter((3, Y, X), (Type[X.type], Type[Int]))
        assertEquals((3, X), ys)
    }

    def testApplied() {
        val y = Applied(TUP1, (x: Int, y: String) => y + x.toString)
        assertEquals("h1", y)
    }

    def testUpdated() {
        val ys = Updated(TUP1, 1, 'p')
        assertEquals((1, 'p'), ys)
    }

    def testToList() {
        val ys: List[Any] = ToList(TUP1)
        assertEquals(List(1, "h"), ys)
    }

    def testFoldLeft() {
        val y = FoldLeft(('a', 2, "hello"), 3, (
            (z: Int, x: Char) => z + 1,
            (z: Int, x: Int) => z + x,
            (z: Int, x: String) => z + x.length) )
        assertEquals(11, y)
    }

    def testFoldLeft0() {
        val y = FoldLeft((), 3, (
            (z: Int, x: Char) => z + 1,
            (z: Int, x: Int) => z + x,
            (z: Int, x: String) => z + x.length) )
        assertEquals(3, y)
    }

    def testReduceLeft() {
        val y = ReduceLeft((3, 'a', 2, "hello"), (
            (z: Int, x: Char) => z + 1,
            (z: Int, x: Int) => z + x,
            (z: Int, x: String) => z + x.length) )
        assertEquals(11, y)
    }

    def testFoldRight() {
        val y = FoldRight(('a', 2, "hello"), 3, (
            (x: Char, z: Int) => z + 1,
            (x: Int, z: Int) => z + x,
            (x: String, z: Int) => z + x.length) )
        assertEquals(11, y)
    }

    def testFoldRight0() {
        val y = FoldRight((), 3, (
            (x: Char, z: Int) => z + 1,
            (x: Int, z: Int) => z + x,
            (x: String, z: Int) => z + x.length) )
        assertEquals(3, y)
    }

    def testReduceRight() {
        val y = ReduceRight(('a', 2, "hello", 3), (
            (x: Char, z: Int) => z + 1,
            (x: Int, z: Int) => z + x,
            (x: String, z: Int) => z + x.length) )
        assertEquals(11, y)
    }

    def testFlatten() {
        val ys = Flatten((
            ('a', 3),
            TUP1
        ))
        assertEquals(('a', 3, 1, "h"), ys)
    }

    def testRange() {
        val ys = Range(2, 5)
        assertEquals((2, 3, 4), ys)
    }

    def testTranpose() {
        val ys = Transpose( ('a', 1, Y), ("h", X, 'b'), (X, 'k', "q") )
        assertEquals( ( ('a', "h", X), (1, X, 'k'), (Y, 'b', "q") ), ys)
    }

    def testTranspose2() {
        val ys = Transpose( ( ('a', "h"), (1, X), (Y, 'b') )  )
        assertEquals( ( ('a', 1, Y), ("h", X, 'b') ), ys)
    }

    def testEquals() {
        val y = Equals( (1, "h"), TUP1 )
        assertTrue(y)

        val z = Equals( (1, "k"), TUP1 )
        assertFalse(z)
    }
}
