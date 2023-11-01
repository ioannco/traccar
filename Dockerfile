FROM gradle:latest AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle assemble --no-daemon

FROM alpine:3.18

ENV TRACCAR_VERSION 5.9

WORKDIR /opt/traccar

RUN set -ex && \
    apk add --no-cache --no-progress openjdk11-jre-headless wget && \
    wget -qO /tmp/traccar.zip https://github.com/traccar/traccar/releases/download/v$TRACCAR_VERSION/traccar-other-$TRACCAR_VERSION.zip && \
    unzip -qo /tmp/traccar.zip -d /opt/traccar && \
    rm /tmp/traccar.zip && \
    apk del wget

COPY --from=build /home/gradle/src/target/*.jar /opt/traccar/

ENTRYPOINT ["java", "-Xms1g", "-Xmx1g", "-Djava.net.preferIPv4Stack=true"]

CMD ["-jar", "tracker-server.jar", "conf/traccar.xml"]