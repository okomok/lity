

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class IsCompilableTest extends org.scalatest.junit.JUnit3Suite {

    Assert{ IsCompilable("()") }
    AssertNot{ IsCompilable("(") }
    AssertNot{ IsCompilable("hello") }

    def testMe() {
    }
}
