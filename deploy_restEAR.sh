#!/bin/bash

#this script locate and go to sample-ear in order to deploy rest.ear after wildfly is already running!
cd './sample-ear'
mvn wildfly:deploy
