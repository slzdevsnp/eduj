#!/bin/bash
ENV=${ENVIRONMENT}
REGISTER="false"
CONNECT_REST_PORT=${CONNECT_REST_PORT:=8080}

echo "[INFO] - Automatic Configuration script running for Kafka Connect connectors for ${ENV} ..."

if [ "${ENV}" == "local" ]; then
  echo "[INFO] - Detected running on Local Environment.  Skipping Auto Registration."
  exit 0
fi

# Go to the path where the java lib is
cd /etc/confluent/docker

URL="http://localhost:${CONNECT_REST_PORT}"

echo "[INFO] - Waiting for for Connect Service to become Available at ${URL}..."

## sleep in bash for loop ##
for i in {1..30}; do
  HTTP_CODE=$(curl --silent --output /dev/null --write-out "%{http_code}" "${URL}")

  if [[ ${HTTP_CODE} -ne 200 ]]; then
    echo "[WARN] - Service Unavailable! - Trying again and trying again [time=$((i * 10))] ..."
  else
    echo "[INFO] - Service Available"
    REGISTER="true"
    break
  fi
  sleep 20s
done

# Check if we can register
if [ ${REGISTER} == "true" ]; then
  echo "[INFO] - Stopping all connectors..."
  CONNECT_URL="${URL}" java -jar connect-cli.jar connector-deregister-all

  echo "[INFO] - Waiting 30 seconds for Connectors to shutdown..."
  sleep 20s

  echo "[INFO] - Starting all connectors..."
  CONNECT_URL="${URL}" java -jar connect-cli.jar connector-register-all
else
  echo "[ERROR] - Failed to detect Connect Service after Skipping connector registration!"
fi
