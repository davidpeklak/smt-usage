import sbt._
import sbt.Keys._
import java.io.File
import smt.SMT

object MyBuild extends Build {

  // define two environments, 'dev' and 'uat'
  lazy val dev = config("dev")
  lazy val uat = config("uat")

  // define the settings of your project, we take over the 'globalSmtSettings' and - for each environment - the smtSettings
  lazy val projSettings = Project.defaultSettings ++ SMT.globalSmtSettings ++ inConfig(dev)(SMT.smtSettings) ++ inConfig(uat)(SMT.smtSettings)

  // define the project
  lazy val proj = Project(id = "smt-usage",
    base = new File("."),
    settings = projSettings
  )
}