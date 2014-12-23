

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class TypeOfTest extends org.scalatest.junit.JUnit3Suite {

    def testMe() {
        val t = Type.of { ???; 3 }
        implicitly[t.apply =:= Int]
    }

    object Make {
        def apply(xs: Any*): Int = 3
    }

    def testFromClass() {
        val t = Type(classOf[Int])
        implicitly[t.apply =:= Int]
    }

    def testTrivial() {
        implicitly[Infer.`3`.apply =:= Int]
        implicitly[Infer.`"hello"`.apply =:= String]
    }

    def testWiden() {
        val x = "hello"
        implicitly[Infer.`x : x.type`.apply =:= String]

        object y
        implicitly[Infer.`y`.apply =:= y.type]
    }
}


