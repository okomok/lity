

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

        Assert { equal(t.intType, classOf[Int]) }
        Assert.not { equal(classOf[String], classOf[Int]) }
    }

    def testEqual() {
        Assert { equal(classBy("g"), classBy("h")) }
        Assert.not { equal(classBy(3), classBy("h")) }
    }

    def testConfroms() {
        class X
        class Y extends X
        Assert { conforms(classBy(new Y), classBy(new X)) }
        Assert.not { conforms(classBy(new X), classBy(new Y)) }
    }

}


