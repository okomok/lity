

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class TypeTest extends org.scalatest.junit.JUnit3Suite {

    def testMe() {
        object t {
            final val intType = classOf[Int]
        }

        lassert { equalsType(t.intType, classOf[Int]) }
        lassertNot { equalsType(classOf[String], classOf[Int]) }
    }

    def testEqual() {
        lassert { equalsType(classBy("g"), classBy("h")) }
        lassertNot { equalsType(classBy(3), classBy("h")) }
    }

    def testConfroms() {
        class X
        class Y extends X
        lassert { conforms(classBy(new Y), classBy(new X)) }
        lassertNot { conforms(classBy(new X), classBy(new Y)) }
    }

}


