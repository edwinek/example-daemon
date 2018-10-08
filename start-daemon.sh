#! /usr/bin/env bash
/usr/bin/jsvc \
    -java-home /usr/lib/jvm/java-8-openjdk-amd64/ \
    -cp /usr/share/java/commons-daemon.jar:/opt/example-daemon/example-daemon.jar \
    uk.edwinek.ExampleSystemDService