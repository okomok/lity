

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class IfTest extends org.scalatest.junit.JUnit3Suite {

    object Hello

    def testTrivial() {
        object f {
            final val value = Fun((X1, X2) -> """`if`(X1 == X2, "1", "Hello")""")
        }

        assertEquals(1, Apply(f.value, (1, 1)))
        assertEquals(Hello, Apply(f.value, (1, 0)))
    }
}
