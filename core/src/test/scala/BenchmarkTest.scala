

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._


trait BenchmarkTest {

    Echo {
        Benchmark("Sleep(500)") - Benchmark("Sleep(200)")
    }


    Echo {
        TypeOf(this)
    }

    //val x = TypeOf(this)
}
