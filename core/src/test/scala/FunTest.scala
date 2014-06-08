

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._
import junit.framework.Assert._


class FunTest extends org.scalatest.junit.JUnit3Suite {

    def testLength0() {
        val y = Apply(Fun(_X1 -> "Tuple.length(_X1)"), (1, 2, 3))
        assertEquals(3, y)
    }

    def testLength1() {
        val y = Apply(ToFun(Tuple.length), (1, 2, 3))
        assertEquals(3, y)
    }

    def testLength2() {
        object Lit {
            final val f = ToFun(Tuple.length)
        }
        val y = Apply(Lit.f, (1, 2, 3))
        assertEquals(3, y)
    }

    def testHigherOrder() {
        object Lit1 {
            final val value  = Fun(_X1 -> "Tuple.length(_X1)")
        }

        object Lit2 {
            final val value = Fun(_X1 -> "Lit1.value")
        }

        val y = Apply( Apply(Lit2.value, "unused"), (1,2,3) )
        assertEquals(3, y)
    }

    def testLambda() {
        object Plus {
            final val value = Fun(_X1 -> Fun(_X2 -> "_X1 + _X2"))
        }

        object Plus3 {
            final val value = Lit{ Apply(Plus.value, 3) }
        }

        val z = Apply(Plus3.value, 5)
        assertEquals(8, z)

        val y = Apply(Apply(Plus.value, 3), 5)
        assertEquals(8, y)

    }

    def testDefer() {
        object Plus {
            final val value = Fun(_X1 -> Fun(_X2 -> "Tuple.length(_X1) + Tuple.length(_X2)"))
        }

        val y = Apply(Apply(Plus.value, (1,2,3)), (1,2,3,4,5))
        assertEquals(8, y)
    }

    def testFun() {
        object Ignore {
            final val value = Fun(_X1 -> "ToFun(Tuple.length)")
        }

        val y = Apply(ToFun(Tuple.length), (1,2,3))
        assertEquals(3, y)

        val z = Apply(Apply(Ignore.value, "h"), (1,2,3))
        assertEquals(3, z)
    }

    def testFun2() {
        object f {
            final val value = Fun(_X1 -> """Apply(Fun(_X2 -> "Tuple.length(_X2)"), _X1)""")
        }

        val y = Apply(f.value, (1,2,3))
        assertEquals(3, y)

    }
}
