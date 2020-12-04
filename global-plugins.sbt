addSbtPlugin("com.timushev.sbt" % "sbt-updates"               % "0.5.1")  // provides `dependencyUpdates`
addSbtPlugin("com.jsuereth"     % "sbt-pgp"                   % "2.1.1")  // for `publishSigned` to maven central
// addSbtPlugin("org.scoverage"    % "sbt-scoverage"             % "1.5.1")
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph"      % "0.10.0-RC1")
addSbtPlugin("com.typesafe.sbt" % "sbt-license-report"        % "1.2.0")
addSbtPlugin("com.github.cb372" % "sbt-explicit-dependencies" % "0.2.16")
addSbtPlugin("org.xerial.sbt"   % "sbt-sonatype"              % "3.9.5")  // publishing to Maven Central (REST release bundles)

