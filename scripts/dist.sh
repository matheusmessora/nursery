#!/usr/bin/env bash
rm -rfv target/
rm -rfv dist/

echo "Packing JAR files"
mvn clean compile assembly:single
echo "Creating dist folder"
mkdir dist
cp target/nursery-web-jar-with-dependencies.jar dist -v

echo "Copying scripts"
cp scripts dist -vr

echo "Copying scripts"
cp scripts dist -vr

echo "Zipping dist folder"
tar -cf nursery.tar dist
