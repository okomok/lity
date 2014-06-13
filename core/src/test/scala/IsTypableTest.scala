

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class IsTypableTest extends org.scalatest.junit.JUnit3Suite {

    Assert{ IsTypable("()") }
    AssertNot{ IsTypable("hello") }

    Intercept("(?s).*ParseException.*") {"""
        IsTypable("(")
    """}

    def testMe() {
    }
}
