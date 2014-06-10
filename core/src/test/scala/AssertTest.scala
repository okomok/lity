

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._
import CompileError._


class AssertTest extends org.scalatest.junit.JUnit3Suite {

    def testError() {
        Assert.errorBy(NotFound) {"""
            wow
        """}
    }

    def testError2() {
        val hey = "hello" // not constant

        Assert.errorBy(IllegalArgument) {"""
            Assert.errorBy(hey) {"3"}
        """}
    }

    def testEqual() {
        Assert { equal(3, 1+2) }
        Assert.errorBy(AssertionFailed) {"""
            Assert { equal(3, "h") }
        """}
    }

    def testConstant() {
        object V {
            final val value = "hey"
            val value2 = "hey"
        }
        Assert { isConstant(V.value) }
        Assert.errorBy("(?s).*constant.*") {"""
            Assert.isConstant(V.value2)
        """}
    }

    def testConforms() {
        class X
        class Y extends X

        Assert { conforms(new Y, new X) }
        Assert.errorBy("(?s).*conform.*") {"""
            Assert.conforms(new X, new Y)
        """}
    }

}


