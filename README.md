# lity 0.1.0-SNAPSHOT

Exploring literal-level metaprogramming


```scala

    Assert {
        Version >= "2.11.0"
    }

    def testExample() {
        Parse {
            If(Version < "2.11.1",
                "oldFoo()",
                "foo()"
            )
        }
    }

```


## Links

* [Browse Source]
* [Browse Test Source]
* [The Scala Programming Language]


Shunsuke Sogame <<okomok@gmail.com>>


[Browse Source]: http://github.com/okomok/lity/tree/master/core/src/main/scala "Browse Source"
[Browse Test Source]: http://github.com/okomok/lity/tree/master/core/src/test/scala "Browse Test Source"
[The Scala Programming Language]: http://www.scala-lang.org/ "The Scala Programming Language"
