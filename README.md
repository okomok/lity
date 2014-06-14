# lity 0.1.0-SNAPSHOT

Exploring literal-level metaprogramming:


```scala
    Assert(ScalaVersion() >= Version("2.11.0"))

    def testExample() {
        Compile {
            if (ScalaVersion() < Version("2.11.1")) {
                "oldFoo()"
            } else {
                "foo()"
            }
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
