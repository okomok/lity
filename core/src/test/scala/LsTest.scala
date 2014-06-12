

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._

class LsTest extends org.scalatest.junit.JUnit3Suite {

    final val x = "hello"
    final val y = ls"$x world"
    final val z = s"$x world"

    Assert { IsConstant(y) }
    AssertNot { IsConstant(z) }
    Assert { Equals("hello world", y) }

    def testMe() {
    }


}
