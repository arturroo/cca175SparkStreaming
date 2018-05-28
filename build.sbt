name := "cca175-spark-streaming"

version := "1.0"

scalaVersion := "2.11.8"

lazy val spark = Seq (
    "spark-core",
    "spark-hive",
    "spark-streaming",
    "spark-sql",
    "spark-streaming-kafka-0-10",
    "spark-sql-kafka-0-10"
).map( "org.apache.spark" %% _ % "2.1.0.cloudera1" % "provided")

// libraryDependencies += "org.apache.spark" %% "spark-sql-kafka-0-10" % "2.1.0.cloudera1"
// libraryDependencies += "org.apache.kafka" % "kafka-clients" % "0.10.0-kafka-2.1.0"
// libraryDependencies += "org.apache.kafka" %% "kafka" % "0.10.0-kafka-2.1.0"
libraryDependencies ++= spark
libraryDependencies += "args4j" % "args4j" % "2.33"

resolvers ++= Seq(
  "Cloudera Repository" at "https://repository.cloudera.com/artifactory/cloudera-repos/"
)

assemblyMergeStrategy in assembly := {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case PathList("org", "apache", "spark", "unused", xs @ _*) => MergeStrategy.first
    case x =>
        val oldStrategy = (assemblyMergeStrategy in assembly).value
        oldStrategy(x)
}

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

// mainClass in assembly := Some("arturro.cca175.spark2.etl.Main")
assemblyJarName in assembly := "cca175-spark-streaming.jar"
        
