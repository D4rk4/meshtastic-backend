# See here for image contents: https://github.com/microsoft/vscode-dev-containers/tree/v0.162.0/containers/ubuntu/.devcontainer/base.Dockerfile

# [Choice] Ubuntu version: bionic, focal
ARG VARIANT="focal"
FROM mcr.microsoft.com/vscode/devcontainers/base:0-${VARIANT}

# [Optional] Uncomment this section to install additional OS packages.
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
     && apt-get -y install openjdk-11-jdk python3-distutils
RUN wget https://github.com/protocolbuffers/protobuf/releases/download/v3.15.8/protoc-3.15.8-linux-x86_64.zip -O /tmp/protoc.zip && cd /tmp && unzip protoc.zip && chmod a+x bin/protoc && cp bin/protoc /usr/local/bin
