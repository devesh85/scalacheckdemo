name := "scalacheck-demo"

scalaVersion := "2.11.5"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.12.2" % "test"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"

testOptions in Test += Tests.Argument(TestFrameworks.ScalaCheck, "-maxSize", "5", "-minSuccessfulTests", "33", "-workers", "1", "-verbosity", "1")

