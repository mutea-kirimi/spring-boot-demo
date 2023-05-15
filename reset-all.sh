#!/bin/sh

docker-compose down
docker-compose up -d
mvn clean install

# cd frontend
# npm install