

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Version {

    def apply(): String = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._

        def apply(): c.Tree = {
            val y = util.Properties.versionString
            q"$y"
        }
    }


    def <=(x: String): Boolean = macro LteqImpl.apply

    final class LteqImpl(override val c: Context) extends InContext {
        import c.universe._
        import scala.math.Ordering.Implicits._

        def apply(x: c.Tree): c.Tree = {
            compareTo(c)(x){ (vs, xs) =>
                vs <= xs
            }
        }
    }


    def >=(x: String): Boolean = macro GteqImpl.apply

    final class GteqImpl(override val c: Context) extends InContext {
        import c.universe._
        import scala.math.Ordering.Implicits._

        def apply(x: c.Tree): c.Tree = {
            compareTo(c)(x){ (vs, xs) =>
                vs >= xs
            }
        }
    }


    def <(x: String): Boolean = macro LtImpl.apply

    final class LtImpl(override val c: Context) extends InContext {
        import c.universe._
        import scala.math.Ordering.Implicits._

        def apply(x: c.Tree): c.Tree = {
            compareTo(c)(x){ (vs, xs) =>
                vs < xs
            }
        }
    }


    def >(x: String): Boolean = macro GtImpl.apply

    final class GtImpl(override val c: Context) extends InContext {
        import c.universe._
        import scala.math.Ordering.Implicits._

        def apply(x: c.Tree): c.Tree = {
            compareTo(c)(x){ (vs, xs) =>
                vs > xs
            }
        }
    }


    def equiv(x: String): Boolean = macro EquivImpl.apply

    final class EquivImpl(override val c: Context) extends InContext {
        import c.universe._
        import scala.math.Ordering.Implicits._

        def apply(x: c.Tree): c.Tree = {
            compareTo(c)(x){ (vs, xs) =>
                vs equiv xs
            }
        }
    }


    private def compareTo(c: Context)(x: c.Tree)(pred: (List[Int], List[Int]) => Boolean): c.Tree = {
        import c.universe._

        val xs = try {
            toIntList(AsString(c)(x))
        } catch {
            case e: MatchError => TypeError(c)("illegal argument", x, "Int literal for version number")
        }
        val y = pred(toIntList(util.Properties.versionString), xs)
        q"$y"
    }


    def toIntList(x: String): List[Int] = {
        val vP = """\D*(\d+)\.(\d+)\.(\d+)(.*)""".r
        val vP(major, minor, patchlevel, rest) = x

        val restInt = if (rest.contains("SNAPSHOT")) {
            Int.MinValue
        } else if (rest.contains("RC")) {
            val rcP = """[^R]+.*RC(\d+).*""".r
            val rcP(n) = rest
            n.toInt
        } else {
            Int.MaxValue
        }

        List(major.toInt, minor.toInt, patchlevel.toInt, restInt)
    }
}
