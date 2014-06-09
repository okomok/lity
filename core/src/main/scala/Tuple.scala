

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Tuple {

    def apply(xs: Any*): Any = macro Impl.apply

    final class Impl(override val c: Context) extends InContext {
        import c.universe._

        def apply(xs: c.Tree*): c.Tree = {
            q"${Here(c)}.Unparse { ${TupleFromList(c)(xs.toList)} }"
        }
    }


    object raw {
        def apply(xs: Any*): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(xs: c.Tree*): c.Tree = TupleFromList(c)(xs.toList)
        }
    }


    object range {
        def apply(n: Any, m: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(n: c.Tree, m: c.Tree): c.Tree = TupleFromList(c) {
                List.range(ExtractInt(c)(n), ExtractInt(c)(m)).map { i =>
                    q"$i"
                }
            }
        }
    }


    object empty {
        def apply(tup: Any): Unit = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree): c.Tree = {
                q"()"
            }
        }
    }


    object equal {
        def apply(tup1: Any, tup2: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup1: c.Tree, tup2: c.Tree): c.Tree = {
                val xs1 = TupleToList(c)(tup1)
                val xs2 = TupleToList(c)(tup2)

                val b = xs1.corresponds(xs2) { (x1, x2) =>
                    x1.equalsStructure(x2)
                }
                q"$b"
            }
        }
    }


    object filter {
        def apply(tup: Any, f: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree, f: c.Tree): c.Tree = TupleFromList(c) {
                TupleToList(c)(tup).filter { x =>
                    AsBoolean(c)(q"${Here(c)}.Apply($f, $x)")
                }
            }
        }
    }


    object find {
        def apply(tup: Any, f: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree, f: c.Tree): c.Tree = {
                val y = TupleToList(c)(tup).find { x =>
                    AsBoolean(c)(q"${Here(c)}.Apply($f, $x)")
                }
                q"$y"
            }
        }
    }


    object flatten {
        def apply(tups: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tups: c.Tree): c.Tree = TupleFromList(c) {
                TupleToList(c)(tups).map { tup =>
                    TupleToList(c)(tup)
                }.flatten
            }
        }
    }


    object foldLeft {
        def apply(tup: Any, z: Any, f: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree, z: c.Tree, f: c.Tree): c.Tree = {
                TupleToList(c)(tup).foldLeft(z) { (z, x) =>
                    q"${Here(c)}.Apply($f, ($z, $x))"
                }
            }
        }
    }


    object foldRight {
        def apply(tup: Any, z: Any, f: Any): Any = macro Impl.apply

         final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree, z: c.Tree, f: c.Tree): c.Tree = {
                TupleToList(c)(tup).foldRight(z) { (x, z) =>
                    q"${Here(c)}.Apply($f, ($x, $z))"
                }
            }
        }
    }


    object get {
        def apply(tup: Any, n: Int): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree, n: c.Tree): c.Tree = {
                val i = ExtractInt(c)(n)
                TupleToList(c)(tup)(i)
            }
        }
    }


    object head {
        def apply(tup: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree): c.Tree = {
                TupleToList(c)(tup).head
            }
        }
    }


    object tail {
        def apply(tup: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree): c.Tree = TupleFromList(c) {
                TupleToList(c)(tup).tail
            }
        }
    }


    object isEmpty {
        def apply(tup: Any): Boolean = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree): c.Tree = {
                val b = TupleToList(c)(tup).isEmpty
                q"$b"
            }
        }
    }


    object length {
        def apply(tup: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree): c.Tree = {
                val n = TupleToList(c)(tup).length
                q"$n"
            }
        }
    }


    object map {
        def apply(tup: Any, f: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree, f: c.Tree): c.Tree = TupleFromList(c) {
                TupleToList(c)(tup).map { x =>
                    q"${Here(c)}.Apply($f, $x)"
                }
            }
        }
    }


    object append {
        def apply(tup1: Any, x: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup1: c.Tree, x: c.Tree): c.Tree = TupleFromList(c) {
                TupleToList(c)(tup1) :+ x
            }
        }
    }


    object prepend {
        def apply(tup1: Any, x: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup1: c.Tree, x: c.Tree): c.Tree = TupleFromList(c) {
                x :: TupleToList(c)(tup1)
            }
        }
    }


    object reduceLeft {
        def apply(tup: Any, f: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree, f: c.Tree): c.Tree = {
                TupleToList(c)(tup).reduceLeft { (z, x) =>
                    q"${Here(c)}.Apply($f, ($z, $x))"
                }
            }
        }
    }


    object reduceRight {
        def apply(tup: Any, f: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree, f: c.Tree): c.Tree = {
                TupleToList(c)(tup).reduceRight { (x, z) =>
                    q"${Here(c)}.Apply($f, ($x, $z))"
                }
            }
        }
    }


    object reverse {
        def apply(tup: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree): c.Tree = TupleFromList(c) {
                TupleToList(c)(tup).reverse
            }
        }
    }


    object toList {
        def apply(tup: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree): c.Tree = {
                val xs = TupleToList(c)(tup)
                q"scala.List(..$xs)"
            }
        }
    }


    object transpose {
        def apply(tups: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tups: c.Tree): c.Tree = TupleFromList(c) {
                TupleToList(c)(tups).map { tup =>
                    TupleToList(c)(tup)
                }.transpose.map { xs =>
                    q"(..$xs)"
                }
            }
        }
    }


    object updated {
        def apply(tup: Any, n: Int, x: Any): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(tup: c.Tree, n: c.Tree, x: c.Tree): c.Tree = TupleFromList(c) {
                val i = ExtractInt(c)(n)
                TupleToList(c)(tup).updated(i, x)
            }
        }
    }


    object fromString {
        def apply(xs: String): Any = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(xs: c.Tree): c.Tree = TupleFromList(c) {
                xs match {
                    case q"${y: String}" => y.toList.map { c => q"$c" }
                }
            }
        }
    }
}
