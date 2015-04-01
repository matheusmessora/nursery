#!/usr/bin/env bash
echo "Packing into JAR files"
mvn clean compile assembly:single
echo "Creating dist folder"
mkdir dist
cp target/nursery-web-jar-with-dependencies.jar dist -v
