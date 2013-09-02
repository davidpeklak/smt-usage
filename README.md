#smt-usage

Exemplary project to demonstrate the usage of smt.

The smt plugin is included by project/project/PluginDef.

This project defines two environments, 'dev' and 'uat'. They are defined in project/MyBuild. For each environment, an H2 database instance and a transformation is defined, see migrations.sbt. This file also defines a sequence of two migrations named '0.0.1' and '0.0.2'.

Run sbt.

Run `dev:show-db-state` to see the state of the dev database instance. Initially, the state will be an empty sequence.

Run `dev:apply-migrations` to apply the migrations to the dev database instance.

Run `dev:show-db-state` again, it will show you the migrations that have been applied.

Change the script src/migrations/s1/up.sql (change the size of the varchar, for example).

Run `dev:apply-migrations` again. Since you have changed a script of the migration '0.0.1', both migrations will be reverted and re-applied.

