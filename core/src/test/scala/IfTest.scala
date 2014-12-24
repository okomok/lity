

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
            final val value = Constant {
                if (v.value == 3) "h" else "g"
            }
        }

        Assert { "h" == f.value }
    }

    def testType() {
        object v {
            final val value = 3
        }
        object f {
            final val value = Constant {
                if (InferClass(v.value) == classOf[Int]) "h" else "g"
            }
        }

        Assert { "h" == f.value }
    }

    def testUnliterable() {
        object v {
            final def value = if (true) 1 else 2
        }

        AssertNot { IsConstant(v.value) }
    }

    def testMacroIf() {
        val x: Hello.type = If(true, Hello, Bye)
        ()
    }

    // Macro selection available.
    Assert {
        IsConstant {
            If(true, Identity, Identity).apply(1)
        }
    }

}
