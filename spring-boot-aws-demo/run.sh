#!/bin/sh

declare project_dir=$(dirname $0)
declare docker_compose_file=${project_dir}/docker-compose.yml
declare app="spring-boot-aws-demo"

function restart() {
    stop
    start
}

function start() {
    echo "Starting $app...."
    build_api
    TMPDIR=/private$TMPDIR docker-compose -f ${docker_compose_file} up --build --force-recreate -d ${app}
    docker-compose -f ${docker_compose_file} logs -f
}

function stop() {
    echo "Stopping $app...."
    docker-compose -f ${docker_compose_file} stop
    docker-compose -f ${docker_compose_file} rm -f
}

function build_api() {
    ./mvnw clean verify
}

function localstack() {
    echo "Starting localstack...."
    TMPDIR=/private$TMPDIR docker-compose -f ${docker_compose_file} up -d localstack
    docker-compose -f ${docker_compose_file} logs -f
}

action="start"

if [[ "$#" != "0"  ]]
then
    action=$@
fi

eval ${action}
