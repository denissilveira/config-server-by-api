FROM java:8
MAINTAINER Denis Silveira<denis.rayan@gmail.com>
VOLUME /tmp
ADD config-server-by-api-0.0.1.jar configserver.jar
RUN bash -c 'touch /configserver.jar'
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/configserver.jar"]