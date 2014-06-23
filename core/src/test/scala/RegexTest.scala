

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class RegexTest extends org.scalatest.junit.JUnit3Suite {

    Intercept("(?s).*PatternSyntaxException.*") {"""
        Regex("{")
    """}

    def testOK() {
        val rx = Regex("h.*o")
        assertTrue {
            rx.pattern.matcher("hello").matches
        }
    }
}


