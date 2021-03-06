#### PostgreSQL Database

init_db.sql file generates the 'location_service' database and creates admin user for the Location Service Admin application. 

##### Before database creation

First run locale to list what locales you are supposed to have and check that the Finnish locale is on the list.

```
$ locale -a | grep fi
```

If you don't see the locale below, you must install a Finnish language pack before creating the database. After the installation Postgres server MUST be restarted.

```
fi_FI.utf8
```

*Ubuntu*

```
$ sudo apt-get install language-pack-fi-base
```

or

```
$ sudo locale-gen fi_FI.UTF-8
$ sudo dpkg-reconfigure locales
```

*Centos*

```
# localedef -i fi_FI -f UTF-8 fi_FI.UTF-8
```

If you see the below error when running the database creation script, it means that the required locale wasn't succesfully installed or Postgres server wasn't restarted after the installation.

```
ERROR:  invalid locale name fi_FI.utf8
\connect: FATAL:  database "location_service" does not exist
```

##### Database creation

How to import the SQL file in the database.

```
su - postgres
psql < init_db.sql
```

Create the database users: loc_ser_login, loc_ser_admin, loc_ser.

```
psql < create_db_users.sql
```

Default passwords for the database users are listed below.

* username: _loc_ser_login_, password: _location_service_login_
  * Used for the admin application's user authentication only
* username: _loc_ser_admin_, password: _location_service_admin_
  * Used by the admin application
* username: _loc_ser_, password: _location_service_
  * Used by the endpoint application

