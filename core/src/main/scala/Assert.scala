

// Copyright Shunsuke Sogame 2014.
// Distributed under the New BSD license.


package com.github.okomok.lity


object Assert {

    def apply(x: Any): Unit = macro Impl.apply

    final class Impl(val c: Context) {
        import c.universe._

        def apply(x: c.Tree): c.Tree = {
            UndeferTree(c)(x) match {
                case q"true" => q"()"
                case y => throw new AssertionError(s"<$x>\n    expected:<true: Boolean(true)> but was:<$y: ${y.tpe.dealias}>")
            }
        }
    }


    object not {
        def apply(x: Any): Unit = macro Impl.apply

        final class Impl(val c: Context) {
            import c.universe._

            def apply(x: c.Tree): c.Tree = {
                UndeferTree(c)(x) match {
                    case q"false" => q"()"
                    case y => throw new AssertionError(s"<$x>\n    expected:<false: Boolean(false)> but was:<$y: ${y.tpe.dealias}>")
                }
            }
        }
    }

/*
    object equal {
        def apply(x: Any, y: Any): Unit = macro Impl.apply

        final class Impl(val c: Context) {
            import c.universe._

            def apply(x: c.Tree, y: c.Tree): c.Tree = {
                if (x.equalsStructure(y)) {
                    q"()"
                } else {
                    throw new AssertionError(s"expected:<$x: ${x.tpe.dealias}> but was:<$y: ${y.tpe.dealias}>")
                }
            }
        }
    }


    object conforms {
        def apply(x: Any, y: Any): Unit = macro Impl.apply

        final class Impl(val c: Context) {
            import c.universe._

            def apply(x: c.Tree, y: c.Tree): c.Tree = {
                if (Type.apply(c)(x) <:< Type.apply(c)(y)) {
                    q"()"
                } else {
                    throw new AssertionError(s"expected:<$x: ${x.tpe.dealias}> conforms to <$y: ${y.tpe.dealias}> but was not.")
                }
            }
        }
    }


    object isConstant {
        def apply(x: Any): Unit = macro Impl.apply

        final class Impl(val c: Context) {
            import c.universe._

            def apply(x: c.Tree): c.Tree = x match {
                case Literal(Constant(_)) => q"()"
                case _ => throw new AssertionError(s"expected:constant but was:<$x: ${x.tpe.dealias}>")
            }
        }
    }
*/

    object errorBy {
        import scala.reflect.macros.TypecheckException

        def apply(r: String)(x: String): Unit = macro Impl.apply

        final class Impl(override val c: Context) extends InContext {
            import c.universe._

            def apply(r: c.Tree)(x: c.Tree): c.Tree = {
                val rgx = ExtractString(c)(r)
                val code = ExtractString(c)(x)

                try {
                    c.typecheck(c.parse(code))
                    CompileError.unexpectedCompile(c)(show(x))
                } catch {
                    case e: TypecheckException => {
                        if (!e.getMessage.matches(rgx)) {
                            CompileError.unexpectedError(c)(s"expected error:<$r> but was:<${e.getMessage}>")
                        }
                    }
                }

                q"()"
            }
        }
    }
}
