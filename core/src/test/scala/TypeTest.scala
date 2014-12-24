

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class TypeTest extends org.scalatest.junit.JUnit3Suite {

    def testMe() {
        object t {
            final val intType = classOf[Int]
            final val intType_ = InferClass(3)
        }

        Assert { t.intType == classOf[Int] }
        Assert { t.intType_ == classOf[Int] }
        AssertNot { classOf[String] == classOf[Int] }
    }

    def testEqual() {
        Assert { InferClass("g") == InferClass("h") }
        AssertNot { InferClass(3) == InferClass("h") }
    }

    def testConforms() {
        class X
        class Y extends X
        Assert { Conforms(InferClass(new Y), InferClass(new X)) }
        AssertNot { Conforms(InferClass(new X), InferClass(new Y)) }
    }

    class Me[T]
    class Your
    type Our = Me[Int]

    Assert {
        classOf[Me[Int]] == classOf[Our]
    }

    def testRuntimeClassOf() {
        assertEquals(classOf[Me[Int]], classOf[Our])
    }
}


