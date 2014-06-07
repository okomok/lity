

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._
import junit.framework.Assert._


class FunTest extends org.scalatest.junit.JUnit3Suite {

    def testLength1() {
        val y = Apply(F_(Length), (1, 2, 3))
        assertEquals(3, y)
    }

    def testLength2() {
        object Lit {
            final val f = F_(Length)
        }
        val y = Apply(Lit.f, (1, 2, 3))
        assertEquals(3, y)
    }

    def testHigherOrder() {
        object Lit1 {
            final val value  = Def_(_X1 -> Length(_X1))
        }

        object Lit2 {
            final val value = Def_(_X1 -> Lit1.value)
        }

        val y = Apply( Apply(Lit2.value, "unused"), (1,2,3) )
        assertEquals(3, y)
    }

    def testLambda() {
        object Plus {
            final val value = Def_(_X1 -> Lambda(_X2 -> (_X2 + _X1)))
        }

        val y = Apply(Apply(Plus.value, 3), 5)
        assertEquals(8, y)
    }

}
