#!/bin/sh

declare project_dir=$(dirname $0)
declare docker_compose_file=${project_dir}/docker-compose-platform.yml


function start() {
    echo 'Starting GeeksClub Platform....'
    docker-compose -f ${docker_compose_file} up -d
    docker-compose -f ${docker_compose_file} logs -f
}

function stop() {
    echo 'Stopping GeeksClub Platform....'
    docker-compose -f ${docker_compose_file} stop
    docker-compose -f ${docker_compose_file} rm -f
}

action="start"

if [ $1 != "0"  ]
then
    action=$@
fi

eval ${action}