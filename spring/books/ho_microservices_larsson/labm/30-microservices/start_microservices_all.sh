#!/usr/bin/env bash

java -jar product-composite-service/target/*.jar &
java -jar product-service/target/*.jar &
java -jar recommendation-service/target/*.jar &
java -jar review-service/target/*.jar &
