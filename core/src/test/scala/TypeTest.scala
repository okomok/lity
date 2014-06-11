

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

        Assert { equalsType(t.intType, classOf[Int]) }
        Assert.not { equalsType(classOf[String], classOf[Int]) }
    }

    def testEqual() {
        Assert { equalsType(classBy("g"), classBy("h")) }
        Assert.not { equalsType(classBy(3), classBy("h")) }
    }

    def testConfroms() {
        class X
        class Y extends X
        Assert { conforms(classBy(new Y), classBy(new X)) }
        Assert.not { conforms(classBy(new X), classBy(new Y)) }
    }

}


