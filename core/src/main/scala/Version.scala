

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Version extends Macro {

    def apply(x: String): Long = macro Impl.apply

    final class Impl(override val c: Context) extends MacroImpl1 {
        import c.universe._

        override protected def impl(x: c.Tree): c.Tree = {
            val y = try {
                encode(AsString(c)(x))
            } catch {
                case e: MatchError => TypeError(c)("illegal argument", x, "String literal for version number")
            }
            q"$y"
        }
    }

    def encode(x: String): Long = {
        encodeIntList(toIntList(x))
    }

    final val SectMax = 999

    def toIntList(x: String): List[Int] = {
        val vP = """\D*(\d+)\.(\d+)\.(\d+)(.*)""".r
        val vP(major, minor, patchlevel, rest) = x

        val restInt = if (rest.contains("SNAPSHOT")) {
            0
        } else if (rest.contains("RC")) {
            val rcP = """[^R]*RC(\d+).*""".r
            val rcP(n) = rest
            1 + n.toInt
        } else { // release version assumed.
            SectMax
        }

        List(major.toInt, minor.toInt, patchlevel.toInt, restInt).ensuring { ns =>
            ns.forall(_ <= SectMax)
        }
    }

    def encodeIntList(ns: List[Int]): Long = {
        ns.reverse.zipWithIndex.map { case (n, i) =>
            n.toLong * math.pow(SectMax+1, i).toLong
        }.reduceLeft(_ + _)
    }
}
