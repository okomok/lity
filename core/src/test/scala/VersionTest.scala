// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class VersionTest extends org.scalatest.junit.JUnit3Suite {

    final val ver = ScalaVersion()

    def testFinal() {
        val ns = Version.toIntList("  2.11.3.final")
        assertEquals(List(2,11,3,999), ns)
    }

    def testTriv() {
        val ns = Version.toIntList("  2.11.3")
        assertEquals(List(2,11,3,999), ns)
    }

    def testRC() {
        val ns = Version.toIntList("  2.11.3-RC7")
        assertEquals(List(2,11,3,7+1), ns)
    }

    def testSnapShot() {
        val ns = Version.toIntList("  2.11.3-SNAPSHOT")
        assertEquals(List(2,11,3,0), ns)
    }

    def testCompare() {

        AssertNot {
            Version(ScalaVersionString()) < Version("2.11.0")
        }

        AssertNot {
            ScalaVersion() < Version("2.11.0")
        }

        Assert {
            Version("3.3.1") < Version("4.3.1")
        }

        Assert {
            Version("3.2.1") < Version("3.2.2")
        }

        Assert {
            Version("998.999.999-RC998") < Version("999.999.999-RC998")
        }

        Assert {
            Version("3.3.1") < Version("3.3.2")
        }

        Assert {
            Version("1.4.2-SNAPSHOT") < Version("1.4.2-RC3")
        }

        Assert {
            Version("1.4.2-SNAPSHOT") < Version("1.4.2")
        }

        Assert {
            Version("1.4.2-final") == Version("1.4.2")
        }

        Assert {
            Version("1.4.1-RC1") < Version("1.4.1")
        }

        Intercept(".*version number.*") {"""
            ScalaVersion() < Version("invalid")
        """}
    }



}


