FROM tomcat:jdk15-adoptopenjdk-openj9
WORKDIR /usr/local/tomcat/webapps
COPY ./target/*.war .
COPY wait-for /
RUN apt-get -q update && apt-get -qy install netcat wget
RUN chmod +x /wait-for
ENTRYPOINT ["/wait-for", "mongo:27017", "--", "catalina.sh", "run"]
