# SPDX-License-Identifier: LGPL-3.0-or-later
FROM docker.io/library/eclipse-temurin:19

ARG UNPACKED_VERSION=6.5

LABEL maintainer="Ilkka Huotari <ilkkah@gmail.com>"

ADD ./languagetool-standalone/target/LanguageTool-"${UNPACKED_VERSION}"/LanguageTool-"${UNPACKED_VERSION}" /LanguageTool
ADD ./fasttext /LanguageTool
ADD ./lid.176.bin /LanguageTool

WORKDIR /LanguageTool

COPY docker-misc/start.sh .
CMD [ "bash", "start.sh" ]
USER nobody
EXPOSE 8010
