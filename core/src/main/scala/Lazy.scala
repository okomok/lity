

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


// See: shapeless.Lazy

// I couldn't find where SLS specifies how implicit-chains work.
// `recName` can really be a valid entry of implicit-lookup?


package com.github.okomok.lity


trait Lazy[T] {
    val value: T
}

object Lazy {
    implicit def of[T]: Lazy[T] = macro LazyImpl.apply[T]
}


final class LazyImpl(override val c: Context) extends InContext {
    import c.universe._

    def apply[T](t: c.WeakTypeTag[T]): c.Tree = {
        val recName = TermName(c.freshName) // fresh name needed. why?

        q"""
        new ${Here(c)}.Lazy[${t.tpe}] {
            implicit val $recName: ${Here(c)}.Lazy[${t.tpe}] = this
            override lazy val value: ${t.tpe} = scala.Predef.implicitly[${t.tpe}]
        }
        """
    }
}
