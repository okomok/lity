

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class LiteralTest extends org.scalatest.junit.JUnit3Suite {

    final val s1 = "hello"
    final val s2 = s1 + " world"
    final val s3 = s"$s1 world"

    Assert { IsConstant(s1) }
    Assert { IsConstant(s2) }
    AssertNot { IsConstant(s3) }

    final val u = ()
    final val t = classOf[Int]

    Assert { IsConstant( () ) }
    AssertNot { IsConstant( u ) }
    AssertNot { IsConstant( Array(1,2,3) ) }
    Assert { IsConstant(t) }
    AssertNot { IsConstant(s"$s1 world") }
    Assert { IsConstant(s1 + " world") }

    def testMe() {
    }

}


