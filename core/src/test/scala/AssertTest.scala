

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._
import CompileError._


class AssertTest extends org.scalatest.junit.JUnit3Suite {

    def testError() {
        expectError(NotFound) {"""
            wow
        """}
    }

    def testError2() {
        val hey = "hello" // not constant

        expectError(IllegalArgument) {"""
            expectError(hey) {"3"}
        """}
    }

    def testEqual() {
        Assert { Equals(3, 1+2) }
        expectError(AssertionFailed) {"""
            Assert { Equals(3, "h") }
        """}
    }

    def testConstant() {
        object V {
            final val value = "hey"
            val value2 = "hey"
        }
        Assert { IsConstant(V.value) }
        expectError("(?s).*IsConstant.*") {"""
            Assert { Defer(IsConstant)(V.value2) }
        """}
    }

    def testConforms() {
        class X
        class Y extends X

        Assert { Conforms(classOf[Y], classOf[X]) }
        expectError("(?s).*Conforms.*") {"""
            Assert { Defer(Conforms)(classOf[X], classOf[Y]) }
        """}
    }

}


