

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomoktest.litytest


import com.github.okomok.lity._

import junit.framework.Assert._


class UndecidableTest extends org.scalatest.junit.JUnit3Suite {

  sealed trait List[+T]
  case class Cons[T](hd: T, tl: List[T]) extends List[T]
  case object Nil extends List[Nothing]

  trait Show[T] {
    def apply(t: T): String
  }

  def show[T](t: T)(implicit s: Show[T]) = s(t)

  implicit def showInt: Show[Int] = new Show[Int] {
    def apply(t: Int) = t.toString
  }

  implicit def showNil: Show[Nil.type] = new Show[Nil.type] {
    def apply(t: Nil.type) = "Nil"
  }

  implicit def showCons[T](implicit st: Lazy[Show[T]], sl: Lazy[Show[List[T]]]): Show[Cons[T]] = new Show[Cons[T]] {
      def apply(t: Cons[T]) = s"Cons(${show(t.hd)(st.value)}, ${show(t.tl)(sl.value)})"
  }

  implicit def showList[T](implicit sc: Lazy[Show[Cons[T]]]): Show[List[T]] = new Show[List[T]] {
    def apply(t: List[T]) = t match {
      case Nil => show(Nil)
      case c: Cons[T] => show(c)(sc.value)
    }
  }

  trait Undefined
  implicit def mkUndefined(implicit x: Lazy[Undefined]): Undefined = new Undefined {}

  def myImplicitly[T](implicit x: T): T = x

  def testMe() {
      show(3)
      show(Nil)

      val l: List[Int] = Cons(1, Cons(2, Cons(3, Nil)))
      val sl = show(l)

//      Predef.implicitly[Show[List[Int]]]
      myImplicitly[Show[List[Int]]]
      val u = myImplicitly[Undefined]
  }

}
