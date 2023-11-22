FROM openjdk:19
LABEL authors="Vadim Taratonov"
EXPOSE 8088
ARG JAR_FILE=/target/audit-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} audit-reactive-kotlin

CMD ["java","-jar","audit-reactive-kotlin"]