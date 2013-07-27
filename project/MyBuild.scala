import sbt._
import sbt.Keys._
import java.io.File
import smt.SMT

object MyBuild extends Build {

  lazy val proj = Project(id = "smt-usage",
    base = new File("."),
    settings = Project.defaultSettings ++ SMT.globalSmtSettings ++ inConfig(dev)(SMT.smtSettings) ++ inConfig(uat)(SMT.smtSettings)
  )

  val dbPassword = SettingKey[String]("db-password", "password for the database")

  lazy val uat = config("uat")
  lazy val dev = config("dev")

}