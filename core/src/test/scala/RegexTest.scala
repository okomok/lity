

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class RegexTest extends org.scalatest.junit.JUnit3Suite {


    implicit class Plus(sc: StringContext) {
        object plus {
            def apply(x: Int, y: Int): Int = x + y
            def unapply(z: Int): Option[(Int, Int)] = Some((1, z-1))
        }
    }

    implicit class Regex(sc: StringContext) {
        def r = {
            val y = new scala.util.matching.Regex(sc.parts.mkString, sc.parts.tail.map(_ => "x"): _*)
            y
        }
    }


    def testMe() {

        val (x, y) = (1, 2)
        assertEquals(3, plus"$x$y")

        val plus"$v$w" = 3
        assertEquals((1, 2), (v, w))

//        val m = new scala.util.matching.Regex("""(\d+)""", sc.parts.tail.map(_ => "x"): _*)
        val r"(\d+)$d"  = "123"
        d.toInt

        assertEquals(
            123,
            "123" match { case r"(\d+)$d" => d.toInt }
        )
    }


}


