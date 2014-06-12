

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class LiteralTest extends org.scalatest.junit.JUnit3Suite {

    final val s1 = "hello"
    final val s2 = s1 + " world"
    final val s3 = s"$s1 world"

    lassert { isConstant(s1) }
    lassert { isConstant(s2) }
    lassertNot { isConstant(s3) }

    final val u = ()
    final val t = classOf[Int]

    lassert { isConstant( () ) }
    lassertNot { isConstant( u ) }
    lassertNot { isConstant( Array(1,2,3) ) }
    lassert { isConstant(t) }
    lassertNot { isConstant(s"$s1 world") }
    lassert { isConstant(s1 + " world") }

    def testMe() {
    }

}


