

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class LListTest extends org.scalatest.junit.JUnit3Suite {

    def testCons() {
        object xs {
            final val value = LCons(3, LCons(2, LNil))
        }

        assertEquals(2, LList.get(xs.value, 1))
    }

    def testRange() {
        object rng3 {
            final val value = LList.rangeFrom(3)
        }

        assertEquals(16, LList.get(rng3.value, 13))
    }
}
