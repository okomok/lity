

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class TupleTest extends org.scalatest.junit.JUnit3Suite {

    final val TUP1 = Unparse{ (1, "h") }

    def testParse() {
        val tup1: (Int, String) = Parse(TUP1)
        ()
    }

    def testGet() {
        val i: Int = Tuple.get(TUP1, 0)
        assertEquals(i, 1)
    }

    object X
    object Y

    def testFlatten0() {
        val xs: (Int, String) = Tuple.flatten(TUP1, ())
        assertEquals(xs._2, "h")
    }

    def testFlatten2() {
        val ys = Tuple.flatten((1, "h"), (X, Y))
        assertEquals((1, "h", X, Y), ys)
    }

    def testAppend() {
        val ys = Tuple.append((1, "h"), X)
        assertEquals((1, "h", X), ys)
    }

    def testPrepend() {
        val ys = Tuple.prepend((1, "h"), X)
        assertEquals((X, 1, "h"), ys)
    }

    def testHead() {
        val i: Int = Tuple.head((1, "h"))
        assertEquals(i, 1)
    }

    def testTail() {
        val ys: (String, Char) = Tuple.tail((1, "h", 'a'))
        assertEquals('a', ys._2)
    }

    def testLength() {
        val n = Tuple.length((1,2,3))
        assertEquals(3, n)
    }

    def testFind() {
        val x: Some[Int] = Tuple.find( (X, 2, Y), Fun( 2 -> "true", (_X1, "false") ) )
        assertEquals(2, x.get)
    }

    def testMap() {
        val ys = Tuple.map((X, 2, Y), (
            (X, "Y")
          , (Y, "X")
          , (2, "3")

        ))
        assertEquals((Y, 3, X), ys)
    }

    def testMap2() {
        val ys = Tuple.map((X, 2, Y), Fun(_I1 -> "_I1 + _I1", _X1 -> "_X1"))
        assertEquals((X, 4, Y), ys)
    }

    def testMap3() {
        val ys = Tuple.map((X, 2, "h"), Fun(_I1 -> "_I1 + _I1", _S1 -> """_S1 + "ello"""", X -> "X"))
        assertEquals((X, 4, "hello"), ys)
    }

    final val PolyFun = Fun(
        X -> "Y"
      , Y -> "X"
      , _I1 -> "_I1 + 1"
    )

    final val YS = Unparse { Tuple.map((X, 2, Y), PolyFun) }

    def testLit() {
        val zs = Tuple.map(YS, Fun(
            (X, "Y")
          , (Y, "X")
          , _I1 -> "_I1 + 1"

        ))

        assertEquals((X, 4, Y), zs)
    }

    def testReverse() {
        val ys = Tuple.reverse((1, "h", 'a'))
        assertEquals(('a', "h", 1), ys)
    }

    def testFilter() {
        val ys = Tuple.filter((3, Y, X), Fun((3, "true"), (X, "true"), (_X1, "false")))
        assertEquals((3, X), ys)
    }

    def testApply() {
        val y = Apply(PolyFun, 3)
        assertEquals(4, y)
    }

    def testUpdated() {
        val ys = Tuple.updated(TUP1, 1, 'p')
        assertEquals((1, 'p'), ys)
    }

    def testToList() {
        val ys: List[Any] = Tuple.toList(TUP1)
        assertEquals(List(1, "h"), ys)
    }

    final val RFun1 = Fun(
        (_C1, _I1) -> "_I1 + 1",
        (_I1, _I2) -> "_I2 + _I1",
        (_S1, _I1) -> "_I1 + _S1.toString.length"
    )

    final val LFun1 = Fun(
        (_I1, _C1) -> "_I1 + 1",
        (_I1, _I2) -> "_I2 + _I1",
        (_I1, _S1) -> "_I1 + _S1.toString.length"
    )

    def testLFun1Apply() {
        val y = Apply(LFun1, (3+1, 2))
        assertEquals(6, y)
    }

    def testFoldLeft() {
        val y = Tuple.foldLeft(('a', 2, "hello"), 3, LFun1)
        assertEquals(11, y)
    }

    def testFoldLeft0() {
        val y = Tuple.foldLeft((), 3, LFun1)
        assertEquals(3, y)
    }

    def testReduceLeft() {
        val y = Tuple.reduceLeft((3, 'a', 2, "hello"), LFun1)
        assertEquals(11, y)
    }

    def testFoldRight() {
        val y = Tuple.foldRight(('a', 2, "hello"), 3, RFun1)
        assertEquals(11, y)
    }

    def testFoldRight0() {
        val y = Tuple.foldRight((), 3, RFun1)
        assertEquals(3, y)
    }

    def testReduceRight() {
        val y = Tuple.reduceRight(('a', 2, "hello", 3), RFun1)
        assertEquals(11, y)
    }

    def testFlatten() {
        val ys = Tuple.flatten((
            ('a', 3),
            TUP1
        ))
        assertEquals(('a', 3, 1, "h"), ys)
    }

    def testRange() {
        val ys = Tuple.range(2, 5)
        assertEquals((2, 3, 4), ys)
    }

    def testTranpose() {
        val ys = Tuple.transpose( ('a', 1, Y), ("h", X, 'b'), (X, 'k', "q") )
        assertEquals( ( ('a', "h", X), (1, X, 'k'), (Y, 'b', "q") ), ys)
    }

    def testTranspose2() {
        val ys = Tuple.transpose( ( ('a', "h"), (1, X), (Y, 'b') )  )
        assertEquals( ( ('a', 1, Y), ("h", X, 'b') ), ys)
    }

    def testEquals() {
        val y = Tuple.equal( (1, "h"), TUP1 )
        assertTrue(y)

        val z = Tuple.equal( (1, "k"), TUP1 )
        assertFalse(z)
    }

    def testFromString() {
        val y = Tuple.fromString("hey")
        assertEquals(('h', 'e', 'y'), y)
    }

    def testIsEmpty() {
        object e {
            final val value = Tuple()
        }

        assertTrue(Tuple.isEmpty(Tuple()))
        assertTrue(Tuple.isEmpty(e.value))
    }
}
