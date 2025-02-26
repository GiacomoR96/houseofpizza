#!/usr/bin/env bash

set -e

if [[ "$1" != "local" && "$1" != "prod" ]]; then
  echo "Warning: Invalid input. Please provide either 'local' or 'prod' for the relevant environment."
  exit 1
fi

ENV="$1"

# Sets the variable for the reference environment
export ENV

[ ! -z "$SKIP_BUILD" ] || ./mvnw clean package -DskipTests

docker build \
  -t houseofpizza:latest \
  --build-arg SOURCE_JAR=./target \
  .

docker compose up --build

# See http://localhost:30008/houseofpizza/swagger-ui/index.html
