#!/bin/bash
CONNECT_REST_ADVERTISED_HOST_NAME=${CONNECT_REST_ADVERTISED_HOST_NAME}
C_HOSTNAME=$(hostname)
ENVIRONMENT=${ENVIRONMENT:local}

if [ ! -z "${CONNECT_REST_ADVERTISED_HOST_NAME}" ]; then
  echo "No Connect Rest setting Hostname ${C_HOSTNAME}"
  CONNECT_REST_ADVERTISED_HOST_NAME="${C_HOSTNAME}"
fi

if [ "${CONNECT_REST_ADVERTISED_HOST_NAME}" == "localhost" ]; then
  echo "Overriding Connect Rest Hostname 'localhost' with ${C_HOSTNAME}"
  CONNECT_REST_ADVERTISED_HOST_NAME="${C_HOSTNAME}"
fi

CONNECT_REST_ADVERTISED_HOST_NAME="${CONNECT_REST_ADVERTISED_HOST_NAME}"

echo "Pre-Launching Configuration Script for connect..."

/etc/confluent/docker/configure-connect.sh &

echo "Starting Connect using hostname ${CONNECT_REST_ADVERTISED_HOST_NAME}..."

/etc/confluent/docker/run

echo "Connect ended!"
