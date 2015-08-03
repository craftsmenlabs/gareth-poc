#!/usr/bin/env bash

DOCKER=$(which docker)
DOCKER_COMPOSE=$(which docker-compose)
SUDO=$(which sudo)

DOCKER_TAG="latest"


# Build tag
buildTag()
{
 local DOCKER_IMAGE_NAME=$1
 local DOCKER_REGISTRY_IMAGE_NAME=$2
 local DOCKER_REGISTRY_TAG=$DOCKER_TAG

 $SUDO $DOCKER tag -f $DOCKER_IMAGE_NAME $DOCKER_REGISTRY_IMAGE_NAME:$DOCKER_REGISTRY_TAG
}

# Build all tags
tagImages()
{
 buildTag architecture_dsl craftsmenlabs/dsl
 buildTag architecture_dashboard craftsmenlabs/dsldashboard
 buildTag architecture_web craftsmenlabs/website
}

# Push image
pushImage()
{
 local DOCKER_REGISTRY_IMAGE_NAME=$1
 local DOCKER_REGISTRY_TAG=$DOCKER_TAG

 $SUDO $DOCKER push $DOCKER_REGISTRY_IMAGE_NAME:$DOCKER_REGISTRY_TAG
}

# Push images
pushImages()
{
 pushImage craftsmenlabs/dsl
 pushImage craftsmenlabs/dsldashboard
 pushImage craftsmenlabs/website
}


# Init and execute script
init()
{
 tagImages
 pushImages
}

init
