

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class EqualsTest extends org.scalatest.junit.JUnit3Suite {

    val y: Int = 3

    def foo(x: Any) = ()

    Assert {
        Equals(foo(y), foo(y))
    }

    AssertNot {
        Equals(foo(y), 3)
    }

    def testMe() {
    }
}


