

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class AlwaysTest extends org.scalatest.junit.JUnit3Suite {

    final val y = Always(3)("hello")

    Assert {
        Equals(3, y)
    }

    def testMe() {
    }
}


