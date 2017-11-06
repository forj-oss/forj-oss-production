# This file is managed by Forjj jenkins plugin at Maintain phase. Do not update it manually!

FROM forjdevops/jenkins

# COPY local_plugins/*  $JENKINS_DATA_REF/plugins/
COPY features.lst /tmp

ENV JENKINS_SLAVE_AGENT_PORT=50000 \
    SEED_JOBS_REPO=https://github.com/forj-oss/forj-oss-infra \
    BUILD_DSL_SCRIPTS=apps/ci/jenkins/jobs-dsl/*.groovy \
    GITHUB_USER=clarsonneur

USER root

COPY *.crt /var/jenkins_home

ARG JENKINS_INSTALL_INITS_URL=https://github.com/forj-oss/jenkins-install-inits/raw/master/

RUN /usr/local/bin/jenkins-install.sh /tmp/features.lst

RUN chown -R jenkins:jenkins $JENKINS_HOME /usr/share/jenkins/ref

USER jenkins
