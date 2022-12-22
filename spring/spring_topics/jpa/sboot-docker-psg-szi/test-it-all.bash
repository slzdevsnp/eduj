#!/usr/bin/env bash

# Sample usage:
#
# ./test-it-all.bash
# OR
# ./test-it-all.bash start stop

: ${HOST=localhost}
: ${PORT=8080}

function assertCurl() {

  local expectedHttpCode=$1
  local curlCmd="$2 -w \"%{http_code}\""
  local result=$(eval $curlCmd)
  local httpCode="${result:(-3)}"
  RESPONSE='' && (( ${#result} > 3 )) && RESPONSE="${result%???}"

  if [ "$httpCode" = "$expectedHttpCode" ]
  then
    if [ "$httpCode" = "200" ]
    then
      echo "Test OK (HTTP Code: $httpCode)"
    else
      echo "Test OK (HTTP Code: $httpCode, $RESPONSE)"
    fi
  else
      echo  "Test FAILED, EXPECTED HTTP Code: $expectedHttpCode, GOT: $httpCode, WILL ABORT!"
      echo  "- Failing command: $curlCmd"
      echo  "- Response Body: $RESPONSE"
      exit 1
  fi
}

function assertEqual() {

  local expected=$1
  local actual=$2

  if [ "$actual" = "$expected" ]
  then
    echo "Test OK (actual value: $actual)"
  else
    echo "Test FAILED, EXPECTED VALUE: $expected, ACTUAL VALUE: $actual, WILL ABORT"
    exit 1
  fi
}

function testUrl() {
    url=$@
    if curl $url -ks -f -o /dev/null
    then
          echo "Ok"
          return 0
    else
          echo -n "not yet"
          return 1
    fi;
}

function waitForService() {
    url=$@
    echo -n "Wait for: $url... "
    n=0
    until testUrl $url
    do
        n=$((n + 1))
        if [[ $n == 100 ]]
        then
            echo " Give up"
            exit 1
        else
            sleep 6
            echo -n ", retry #$n "
        fi
    done
}

function recreateEntity() {
    local customerId=$1
    local body=$2

    assertCurl 200 "curl -X DELETE http://$HOST:$PORT/customer/${customerId} -s"
    curl -X POST http://$HOST:$PORT/customer -H "Content-Type: application/json" --data "$body"
}

function setupTestData(){
  body=\
'{"customerId": 1, "firstName":"John", "lastName":"Doe"}'
  recreateEntity 1 "$body"

  body=\
'{"customerId": 2, "firstName":"Jane", "lastName":"Dunhill"}'
  recreateEntity 2 "$body"

  body=\
'{"customerId": 3, "firstName":"Joe", "lastName":"McDonald"}'
  recreateEntity 3 "$body"

}


set -e

echo "HOST=${HOST}"
echo "PORT=${PORT}"

if [[ $@ == *"start"* ]]
then
    echo "Restarting the test environment..."
    echo "$ docker-compose down"
    docker-compose down
    echo "$ docker-compose up -d"
    docker-compose up -d
fi

waitForService curl -X DELETE http://$HOST:$PORT/customer/1

# write in  test data records
setupTestData

## -------- suite of tests start --------
# Verify that a normal request works,
assertCurl 200 "curl -X GET http://$HOST:$PORT/customer/1 -s"
assertEqual 1 $(echo $RESPONSE | jq .customerId)

# Verify that a 404 (Not Found) error is returned for a non existing productId (13)
assertCurl 404 "curl http://$HOST:$PORT/customer/13 -s"

# Verify that a 422 (Unprocessable Entity) error is returned for a productId that is out of range (-1)
assertCurl 422 "curl http://$HOST:$PORT/customer/-1 -s"

# Verify that a 400 (Bad Request) error error is returned for a productId that is not a number, i.e. invalid format
assertCurl 400 "curl http://$HOST:$PORT/customer/invalidCustomerId -s"
#echo "DBG: last RESPONSE: ${RESPONSE}"  # paste  a dbg msg before the failing test
assertEqual "\"Bad Request\"" "$(echo $RESPONSE | jq .error)"




## -------- suite of tests end --------

if [[ $@ == *"stop"* ]]
then
    echo "We are done, stopping the test environment..."
    echo "$ docker-compose down"
    docker-compose down
fi

echo "End:" `date`

