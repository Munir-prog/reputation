FROM alpine:3 as base
RUN apk add git openjdk17
ARG CACHEBUST=1
RUN git clone https://github.com/Munir-prog/reputation.git && pwd
WORKDIR /reputation
RUN ./mvnw package

FROM alpine:3 as result
RUN apk add openjdk17
WORKDIR /reputation
COPY --from=base /reputation/target/reputation*.jar ./app.jar
# COPY application-dev.yaml .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
# CMD ["--spring.config.location=classpath:/application.yml,file:application-dev.yaml"]
