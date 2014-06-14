

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class ConstantTest extends org.scalatest.junit.JUnit3Suite {

    final val b = true
    final val i = 12
    final val s = "12"

    Assert {
        IsConstant {
            Constant { i == 2 }
        }
    }

    Assert {
        IsConstant {
            Constant { s == "12" }
        }
    }

    Assert {
        IsConstant {
            Constant { if (b) 1 else 2 }
        }
    }

    Assert {
        IsConstant {
            Constant { if (b) 1 }
        }
    }


    def testMe() {
    }
}


