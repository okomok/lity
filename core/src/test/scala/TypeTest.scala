

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

        Assert { t.intType == classOf[Int] }
        AssertNot { classOf[String] == classOf[Int] }
    }

    def testEqual() {
        Assert { ClassBy("g") == ClassBy("h") }
        AssertNot { ClassBy(3) == ClassBy("h") }
    }

    def testConforms() {
        class X
        class Y extends X
        Assert { Conforms(ClassBy(new Y), ClassBy(new X)) }
        AssertNot { Conforms(ClassBy(new X), ClassBy(new Y)) }
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


