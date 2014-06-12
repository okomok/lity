

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

        Assert { EqualsType(t.intType, classOf[Int]) }
        AssertNot { EqualsType(classOf[String], classOf[Int]) }
    }

    def testEqual() {
        Assert { EqualsType(ClassBy("g"), ClassBy("h")) }
        AssertNot { EqualsType(ClassBy(3), ClassBy("h")) }
    }

    def testConfroms() {
        class X
        class Y extends X
        Assert { Conforms(ClassBy(new Y), ClassBy(new X)) }
        AssertNot { Conforms(ClassBy(new X), ClassBy(new Y)) }
    }

}


