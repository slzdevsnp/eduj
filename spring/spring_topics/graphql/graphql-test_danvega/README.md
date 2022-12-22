# Spring for GraphQL CRUD + Testing

This repository contains code for the following YouTube videos:

- [GraphQL CRUD Java](https://youtu.be/AgSO3rcSuHE)
- [GraphQL API Testing](https://youtu.be/0b0x3C_BTT8)

## testing with requests
* start the spring boot app
* run requests in requests.http


## github repo
https://github.com/danvega/graphql-test.git

## jdk
this project uses jdk-17 which is specified in .sdkmnrc
run sdk env # to set this jdk


## Tests
NB!. if you need to test http layer, need webflux dependency to use HttpClient

CoffeeControllerIntTest is  integration test  using GraphQlTester for crud api

see how assertions are implemented in different tests
