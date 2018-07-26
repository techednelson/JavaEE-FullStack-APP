#!/bin/bash

#perform mvn clean install command in order to build rest.ear
mvn clean install

#locate and go to liquibase pom.xml file
cd './liquibase'

#run changeLog-master.xml via maven command for database creation
mvn liquibase:update
