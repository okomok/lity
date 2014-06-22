

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class FileTest extends org.scalatest.junit.JUnit3Suite {

    final val file = FileName()

    Assert {
        "FileTest.scala" == file
    }

    final val path = File()

    def testMe() {
        assertEquals("filetest" + java.io.File.separator + "FileTest.scala", path)
    }

}


