

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class IfTest extends org.scalatest.junit.JUnit3Suite {

    object Hello
    object Bye

    def testTrivial() {
        object v {
            final val value = 3
        }
        object f {
            final val value = If(Equals(v.value, 3), "h", "g")
        }

        Assert { Equals("h", f.value) }
    }

    def testType() {
        object v {
            final val value = 3
        }
        object f {
            final val value = If(EqualsType(ClassBy(v.value), classOf[Int]), "h", "g")
        }

        Assert { Equals("h", f.value) }
    }
}
