

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._

class ConstantStringTest extends org.scalatest.junit.JUnit3Suite {

    final val x = "hello"
    final val y = cs"$x world"
    final val z = s"$x world"

    Assert { IsConstant(y) }
    AssertNot { IsConstant(z) }
    Assert { "hello world" == y }

    def testMe() {
    }


}
