import smt._

database in dev := new H2Database({
 Class.forName("org.h2.Driver")
 java.sql.DriverManager.getConnection("jdbc:h2:~/test_dev")
})

database in uat := new H2Database({
 Class.forName("org.h2.Driver")
 java.sql.DriverManager.getConnection("jdbc:h2:~/test_uat")
})

migrations <<= (migrationsSource) map ( m => Seq(
  FileMigration("0.0.1", Seq(m / "s1", m / "s2")),
  FileMigration("0.0.2", Seq(m / "s3"))
))

transformations in dev := Seq((s: String) => s.replaceAllLiterally("$TABLE", "DEVTAB"))

transformations in uat := Seq((s: String) => s.replaceAllLiterally("$TABLE", "UATTAB"))
