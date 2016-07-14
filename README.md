# Yildiz-Engine module-database.

This is the official repository of The Yildiz-Engine Database Module, part of the Yildiz-Engine project.
The database module is a component meant to access and use the database.

## Status [![Quality Gate](https://www.sonarqube.com/api/badges/gate?key=be.yildiz-games:module-database)](https://sonarqube.com/overview?id=be.yildiz-games:module-database)

## Features

* Mysql support.
* C3P0 pooling support.
* Model construction from physical model with JOOQ.
* ...

## Requirements

To build this module, you will need a java 8 JDK, and Maven 3.

## Coding Style and other information

Project website:
http://www.yildiz-games.be

Issue tracker:
https://yildiz.atlassian.net

Wiki:
https://yildiz.atlassian.net/wiki

Quality report:
https://sonarqube.com/overview?id=be.yildiz-games:module-database

## License

All source code files are licensed under the permissive MIT license
(http://opensource.org/licenses/MIT) unless marked differently in a particular folder/file.
## Build instructions for module-graphic using maven.

Go to your root directory, where you POM file is located.

Then invoke maven

	mvn clean install

This will compile the source code, then run the unit tests, and finally build a jar file.

## Usage

To use the snapshot versions, please add the following repository
https://oss.sonatype.org/content/repositories/snapshots/

Released version are retrieved from maven central.

In your maven project, add the dependency

```xml
<dependency>
    <groupId>be.yildiz-games</groupId>
    <artifactId>module-database</artifactId>
    <version>1.0.0-0-SNAPSHOT</version>
</dependency>
```
## Contact
Owner of this repository: Grégory Van den Borre