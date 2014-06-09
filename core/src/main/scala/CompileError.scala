

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object CompileError {
    final val AnyError = "(?s).*"
    final val AbstractType = "(?s)abstract type.*"
    final val AssertionFailed = "(?si).*assertion.*"
    final val CannotProve = "(?si).*prove.*"
    final val UnexpectedCompile = "(?s)compiles unexpectedly.*"
    final val UnexpectedError = "(?s)error unexpectedly.*"
    final val IllegalArgument = "(?si).*illegal argument.*"
    final val NotFound = "(?si).*not found.*"
    final val NothingType = "(?s)Nothing type.*"
    final val TypeMismatch = "(?si).*type mismatch.*"

    def assertError(c: Context)(msg: String): Nothing = {
        c.abort(c.enclosingPosition, "assertion failed: " + msg)
    }

    def illegalArgument(c: Context)(msg: String): Nothing = {
        c.abort(c.enclosingPosition, "illegal argument: " + msg)
    }

    def abstractType(c: Context)(msg: String): Nothing = {
        c.abort(c.enclosingPosition, "abstract type: " + msg)
    }

    def nothingType(c: Context)(msg: String): Nothing = {
        c.abort(c.enclosingPosition, "Nothing type: " + msg)
    }

    def unexpectedCompile(c: Context)(msg: String): Nothing = {
        c.abort(c.enclosingPosition, "compiles unexpectedly:\n" + msg)
    }

    def unexpectedError(c: Context)(msg: String): Nothing = {
        c.abort(c.enclosingPosition, "error unexpectedly:\n" + msg)
    }
}
