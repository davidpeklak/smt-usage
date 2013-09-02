import smt._

// This file references two environments ,'dev' and 'uat', which are
// defined as sbt configurations in project/MyBuild

// define the database instance in the 'dev' environment
database in dev := new H2Database({
 Class.forName("org.h2.Driver")
 java.sql.DriverManager.getConnection("jdbc:h2:~/test_dev")
})

// define the database instance in the 'uat' environment
database in uat := new H2Database({
 Class.forName("org.h2.Driver")
 java.sql.DriverManager.getConnection("jdbc:h2:~/test_uat")
})

// define the sequence of migrations. We define two migrations '0.0.1' and '0.0.2' as
// FileMigrations, where the path is given relative to the 'migrationsSource' path.
// 'migrationsSource' is src/main/migrations
migrations <<= (migrationsSource) map ( m => Seq(
  FileMigration("0.0.1", Seq(m / "s1", m / "s2")),
  FileMigration("0.0.2", Seq(m / "s3"))
))

// define the sequence of transformations in the 'dev' environment. The sequence consists
// of only one transformation, which replaces the literal string '$TABLE' with 'DEVTAB'
// in all the scripts of the migrations
transformations in dev := Seq((s: String) => s.replaceAllLiterally("$TABLE", "DEVTAB"))

// define the sequence of transformations in the 'uat' environment. Same as above, but
// 'UATTAB'
transformations in uat := Seq((s: String) => s.replaceAllLiterally("$TABLE", "UATTAB"))
