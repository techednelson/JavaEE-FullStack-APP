#!/bin/bash

#locate and go to liquibase pom.xml file
cd '..'
cd '..'
cd '..'
cd '..'

#run changeLog-master.xml via maven command for database creation
mvn liquibase:update

#locate and go to parent pom.xml file
cd '..'

#perform mvn clean install command in order to build rest.ear
mvn clean install