#!/bin/bash
#
#

REPO=$LOGNAME
IMAGE_NAME="forj-oss-jenkins"
REPO=forj-oss

OFFICIAL_VERSION=V0

if [[ "$DEV_USER" = "" ]]
then
    echo "Not used in Forjj context. Using $LOGNAME as DEV_USER"
    DEV_USER=$LOGNAME
fi

IMAGE_VERSION=$OFFICIAL_VERSION


# For Docker Out Of Docker case, a docker run may provides the SRC to use in place of $(pwd)
# This is required in case we use the docker -v to mount a 'local' volume (from where the docker daemon run).
if [ "$SRC" != "" ]
then
    VOL_PWD="$SRC"
else
   VOL_PWD="$(pwd)"
fi

if [ "$http_proxy" != "" ]
then
   PROXY=" --env http_proxy=$http_proxy --env https_proxy=$https_proxy --env no_proxy=$no_proxy"
   echo "Using your local proxy setting : $http_proxy"
   if [ "$no_proxy" != "" ]
   then
      PROXY="$PROXY -e no_proxy=$no_proxy"
      echo "no_proxy : $no_proxy"
   fi
fi

if [ -f run_opts.sh ]
then
   echo "loading run_opts.sh..."
   source run_opts.sh
fi

# Loading deployment environment ($1)
if [ -f source_$1.sh ]
then
   echo "Loading deployment environment '$1'"
   source source_$1.sh
fi

if [ "$SERVICE_ADDR" = "" ]
then
   SERVICE_ADDR="localhost"
   echo "SERVICE_ADDR not defined by any deployment environment. Set to '$SERVICE_ADDR'"
fi
if [ "$SERVICE_PORT" = "" ]
then
   SERVICE_PORT=8083
   echo "SERVICE_PORT not defined by any deployment environment. Set to '$SERVICE_PORT'"
fi

export JENKINS_URL="https://jenkins-forjj.famille-larsonneur.eu"

TAG_NAME=hub.docker.com/$REPO/$IMAGE_NAME:$IMAGE_VERSION

CONTAINER_IMG="$(docker ps -a -f name=forj-oss-jenkins-dood --format "{{ .Image }}")"

IMAGE_ID="$(docker images --format "{{ .ID }}" $IMAGE_NAME)"

if [[ "$SIMPLE_ADMIN_PWD" != "" ]]
then
   export SIMPLE_ADMIN_PWD
   ADMIN="-e SIMPLE_ADMIN_PWD"
   unset ADMIN_PWD
   echo "Admin password set."
fi

if [[ "$GITHUB_PASS" != "" ]]
then
   export  GITHUB_PASS
   GITHUB_USER="-e GITHUB_PASS"
   echo "Github user password set."
fi

JENKINS_MOUNT="-v forj-oss-jenkins-home:/var/jenkins_home -e DOCKER_JENKINS_MOUNT=forj-oss-jenkins-home:/var/jenkins_home"


if [ "$CONTAINER_IMG" != "" ]
then
    if [ "$CONTAINER_IMG" != "$TAG_NAME" ] && [ "$CONTAINER_IMG" != "$IMAGE_ID" ]
    then
        # TODO: Find a way to stop it safely - Using safe shutdown?

        docker rm -f jenkins-restart
        docker run -id --name jenkins-restart $DOCKER_DOOD $GITHUB_USER $ADMIN alpine /bin/cat
        echo "#!/bin/sh
sleep 30
docker rm -f forj-oss-jenkins-dood
sleep 2

docker run --restart always $DOCKER_DOOD -d -p $SERVICE_PORT:8080 $JENKINS_MOUNT --name forj-oss-jenkins-dood -e JENKINS_URL $GITHUB_USER $ADMIN $PROXY $TAG_NAME
echo 'Service is restarted'
sleep 1
docker rm -f jenkins-restart" > do_restart.sh
        docker cp do_restart.sh jenkins-restart:/tmp/do_restart.sh
        rm -f do_restart.sh
        docker exec jenkins-restart chmod +x /tmp/do_restart.sh

        echo "The image has been updated. It will be restarted in about 30 seconds"
        set -x
        docker exec jenkins-restart /tmp/do_restart.sh
        set +x
    else
        echo "Nothing to re/start. Jenkins is still accessible at $JENKINS_URL"
    fi
    exit 0
fi

# No container found. Start it.

eval docker run --restart always $DOCKER_DOOD -d -p $SERVICE_PORT:8080 $JENKINS_MOUNT --name forj-oss-jenkins-dood -e JENKINS_URL $GITHUB_USER $ADMIN $PROXY $TAG_NAME

if [ $? -ne 0 ]
then
    echo "Issue about jenkins startup."
    docker logs forj-oss-jenkins-dood
    exit 1
fi
echo "Jenkins has been started and should be accessible at $JENKINS_URL"
