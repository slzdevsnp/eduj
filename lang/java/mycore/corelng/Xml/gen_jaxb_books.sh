#!/usr/bin/env bash

echo "cleaning bindings"
if [ -d src/main/java/org/slzdevsnp/binding/books ] ; then
  rm -rf src/main/java/org/slzdevsnp/binding/books
fi
xjc -d src/main/java -p org.slzdevsnp.binding.books src/main/resources/books.xsd