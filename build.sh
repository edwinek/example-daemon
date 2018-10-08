#! /usr/bin/env bash
docker stop daemon_container
docker rm daemon_container
docker build -t jsvc_image .
docker run --rm --name daemon_container -tid -v "$(pwd)":/src jsvc_image bash
docker exec -ti daemon_container bash -c "/src/gradlew clean jar -p /src"
docker exec -ti daemon_container bash