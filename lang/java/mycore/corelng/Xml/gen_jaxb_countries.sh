#!/usr/bin/env bash

echo "cleaning bindings"
if [ -d src/main/java/org/slzdevsnp/binding/countriesa ] ; then
  rm -rf src/main/java/org/slzdevsnp/binding/countriesa
fi

xjc -d src/main/java -p org.slzdevsnp.binding.countriesa src/main/resources/countries.xsd