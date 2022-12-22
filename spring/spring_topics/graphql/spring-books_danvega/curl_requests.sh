#!/usr/bin/env bash


# all books with 1 field
curl -s -X POST http://localhost:8080/graphql \
 -H "Content-Type: application/json" \
 -d '{"query": "query {allBooks { title } }"}'


# all books with more fiields
curl -s -X POST http://localhost:8080/graphql \
 -H "Content-Type: application/json" \
 -d '{"query": "query {allBooks {title pages rating {star} author {firstName lastName } } }"}'



##  a query with a paramer
curl -s -X POST http://localhost:8080/graphql \
 -H "Content-Type: application/json" \
 -d '{"query": "query {findOne(id: 1) {title pages rating {star } author {firstName lastName } } }"}'
