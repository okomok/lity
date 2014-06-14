

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class LineTest extends org.scalatest.junit.JUnit3Suite {

    final val line = Line()

    Assert {
        17 == line
    }

    def testMe() {
    }

}


