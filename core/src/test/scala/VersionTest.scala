// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class VersionTest extends org.scalatest.junit.JUnit3Suite {

    final val ver = ScalaVersion()

    def testFinal() {
        val ns = ScalaVersion.toIntList("  2.11.3.final")
        assertEquals(List(2,11,3,Int.MaxValue), ns)
    }

    def testTriv() {
        val ns = ScalaVersion.toIntList("  2.11.3")
        assertEquals(List(2,11,3,Int.MaxValue), ns)
    }

    def testRC() {
        val ns = ScalaVersion.toIntList("  2.11.3-RC7")
        assertEquals(List(2,11,3,7), ns)
    }

    def testSnapShot() {
        val ns = ScalaVersion.toIntList("  2.11.3-SNAPSHOT")
        assertEquals(List(2,11,3,Int.MinValue), ns)
    }

    def testCompare() {

        Assert {
            ScalaVersion < "2.99.0"
        }

        AssertNot {
            ScalaVersion < "2.11.0"
        }

        Intercept(".*version number.*") {"""
            ScalaVersion < "invalid"
        """}
    }



}


