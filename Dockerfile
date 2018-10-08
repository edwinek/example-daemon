FROM ubuntu:18.04
ADD start-daemon.sh /
RUN apt update && \
    apt install openjdk-8-jre-headless \
                openjdk-8-jdk-headless \
                jsvc -y && \
    chmod +x /start-daemon.sh