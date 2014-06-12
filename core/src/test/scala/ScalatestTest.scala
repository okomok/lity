

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import org.scalatest.Assertions._
import junit.framework.Assert


class ScalatestTest extends org.scalatest.junit.JUnit3Suite {

    def foo() = false

    def testTriv() {

    }
/*
    def testMe() {
        val a = 3
        assert(isConstant(a)) // no informative...
    }

    def testScalatest() {
        assert(foo())
    }

    def testJunit() {
        Assert.assertTrue(foo())
    }
*/
}


