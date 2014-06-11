

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
        lassert { lequals(3, 1+2) }
        expectError(AssertionFailed) {"""
            lassert { lequals(3, "h") }
        """}
    }

    def testConstant() {
        object V {
            final val value = "hey"
            val value2 = "hey"
        }
        lassert { isConstant(V.value) }
        expectError("(?s).*isConstant.*") {"""
            lassert { defer(isConstant)(V.value2) }
        """}
    }

    def testConforms() {
        class X
        class Y extends X

        lassert { conforms(classOf[Y], classOf[X]) }
        expectError("(?s).*conforms.*") {"""
            lassert { defer(conforms)(classOf[X], classOf[Y]) }
        """}
    }

}


