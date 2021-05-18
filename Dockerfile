FROM openjdk:16-alpine3.13
COPY ./ /tmp
WORKDIR /tmp
RUN javac /tmp/FilosofosMain.java
ENTRYPOINT ["java", "FilosofosMain"]