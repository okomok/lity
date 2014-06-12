

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class ExampleTest extends org.scalatest.junit.JUnit3Suite {

    final val IsDebug = false

    final val MyClass = If(IsDebug, classOf[Int], classOf[String])

    val t = Type(MyClass)
    type My = t.apply

    Assert {
        EqualsType(classOf[My], classOf[String])
    }

    def testExample() {
    }


}


