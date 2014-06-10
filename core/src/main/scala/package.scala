

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok


package object lity {
    final val here = "com.github.okomok.lity" // Don't add `_root_` for `showCode`

    type Context = scala.reflect.macros.whitebox.Context

    type Unspecified = Any

    final val LNil = here + ".LNil_()"

    implicit class StringInterpolator(val sc: StringContext) extends AnyVal {
        def ls(args: Any*): String = macro StringInterpolatorImpl.apply
    }
}
