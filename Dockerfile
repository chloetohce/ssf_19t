FROM openjdk:23-jdk-oracle AS builder

ARG COMPILE_DIR=/compiledir

WORKDIR ${COMPILE_DIR}

COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .
COPY .mvn .mvn
COPY src src

RUN ./mvnw clean package -Dmaven.test.skip=true

ENV SERVER_PORT=8080

EXPOSE ${SERVER_PORT}

# i don't need this anymore
# as app will run in second stage
# ENTRYPOINT java -jar target/ssf_18l-0.0.1-SNAPSHOT.jar

# day 18 - slide 13
# second stage
FROM openjdk:23-jdk-oracle

ARG WORK_DIR=/app

WORKDIR ${WORK_DIR}

COPY --from=builder /compiledir/target/ssf_19t-0.0.1-SNAPSHOT.jar vttp_ssf-19t.jar

ENV SERVER_PORT=8080

EXPOSE ${SERVER_PORT}

ENTRYPOINT ["java","-jar", "vttp_ssf-19t.jar"]