

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class TypeOfTest extends org.scalatest.junit.JUnit3Suite {

    def testMe() {
        val t = Type.of(3)
        implicitly[t.unwrap =:= Int]
    }

    object Make {
        def apply(xs: Any*): Int = 3
    }

    def testTest() {
    }

}


