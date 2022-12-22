#!/usr/bin/env bash
echo "cleaning bindings"
if [ -d src/main/java/org/slzdevsnp/binding/tmtrading ] ; then
  rm -rf src/main/java/org/slzdevsnp/binding/tmtrading
fi
xjc -d src/main/java -p org.slzdevsnp.binding.tmtrading src/main/resources/tm-trading-v69.xsd