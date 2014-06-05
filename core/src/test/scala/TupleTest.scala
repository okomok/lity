

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

    def testFind() {
        val x: Some[Int] = Find( (X, 2, Y), ( 2 -> true, (_X1, false) ) )
        assertEquals(2, x.get)
    }

    def testMap() {
        val ys = Map((X, 2, Y), (
            (X, Y)
          , (Y, X)
          , (2, 3)

        ))
        assertEquals((Y, 3, X), ys)
    }

    def testMap2() {
        val ys = Map((X, 2, Y), (_I1 -> (_I1 + _I1), _X1 -> _X1))
        assertEquals((X, 4, Y), ys)
    }

    def testMap3() {
        val ys = Map((X, 2, "h"), (_I1 -> (_I1 + _I1), _S1 -> (_S1 + "ello"), X -> X))
        assertEquals((X, 4, "hello"), ys)
    }

    final val PolyFun = L_ { (
        X -> Y
      , Y -> X
      , _I1 -> (_I1 + 1)
    ) }

    final val YS = L_ { Map((X, 2, Y), PolyFun) }

    def testLiteralize() {
        val zs = Map(YS, (
            (X, Y)
          , (Y, X)
          , _I1 -> (_I1 + 1)

        ))

        assertEquals((X, 4, Y), zs)
    }

    def testReverse() {
        val ys = Reverse((1, "h", 'a'))
        assertEquals(('a', "h", 1), ys)
    }

    def testFilter() {
        val ys = Filter((3, Y, X), ((3, true), (X, true), (_X1, false)))
        assertEquals((3, X), ys)
    }

    def testApply() {
        val y = Apply(PolyFun, 3)
        assertEquals(4, y)
    }

    def testUpdated() {
        val ys = Updated(TUP1, 1, 'p')
        assertEquals((1, 'p'), ys)
    }

    def testToList() {
        val ys: List[Any] = ToList(TUP1)
        assertEquals(List(1, "h"), ys)
    }

    final val LFun1 = L_{ (
        (_I1, _C1) -> (_I1 + 1),
        (_I1, _I2) -> (_I2 + _I1),
        (_I1, _S1) -> (_I1 + _S1.toString.length)
    )}

    final val RFun1 = L_{ (
        (_C1, _I1) -> (_I1 + 1),
        (_I1, _I2) -> (_I2 + _I1),
        (_S1, _I1) -> (_I1 + _S1.toString.length)
    )}

    def testFoldLeft() {
        val y = FoldLeft(('a', 2, "hello"), 3, LFun1)
        assertEquals(11, y)
    }

    def testFoldLeft0() {
        val y = FoldLeft((), 3, LFun1)
        assertEquals(3, y)
    }

    def testReduceLeft() {
        val y = ReduceLeft((3, 'a', 2, "hello"), LFun1)
        assertEquals(11, y)
    }

    def testFoldRight() {
        val y = FoldRight(('a', 2, "hello"), 3, RFun1)
        assertEquals(11, y)
    }

    def testFoldRight0() {
        val y = FoldRight((), 3, RFun1)
        assertEquals(3, y)
    }

    def testReduceRight() {
        val y = ReduceRight(('a', 2, "hello", 3), RFun1)
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

    def testToTuple() {
        val y = ToTuple("hey")
        assertEquals(('h', 'e', 'y'), y)
    }
}
