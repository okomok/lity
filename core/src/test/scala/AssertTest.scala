

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._
import CompileError._


class AssertTest extends org.scalatest.junit.JUnit3Suite {

    def testError() {
        Intercept(NotFound) {"""
            wow
        """}
    }

    def testError2() {
        val hey = "hello" // not constant

        Intercept(IllegalArgument) {"""
            Intercept(hey) {"3"}
        """}
    }

    def testEqual() {
        Assert { 3 == (1+2) }
        Intercept(AssertionFailed) {"""
            Assert { 3 == "h" }
        """}
    }

    def testConstant() {
        object V {
            final val value = "hey"
            val value2 = "hey"
        }
        Assert { IsConstant(V.value) }
        Intercept("(?s).*IsConstant.*") {"""
            Assert { "IsConstant(V.value2)" }
        """}
    }

    def testConforms() {
        class X
        class Y extends X

        Assert { Conforms(classOf[Y], classOf[X]) }
        Intercept("(?s).*Conforms.*") {"""
            Assert { "Conforms(classOf[X], classOf[Y])" }
        """}
    }

}


