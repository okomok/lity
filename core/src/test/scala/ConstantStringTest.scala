

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._

class ConstantStringTest extends org.scalatest.junit.JUnit3Suite {

    final val x = "hello"
    final val y = cs"$x world"

    Assert.isConstant(y)
    Assert.equal("hello world", y)

    def testTrivial() {
        assertEquals("hello world", y)
    }

}
