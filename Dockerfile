FROM gradle:jdk17-alpine as build
WORKDIR /app
COPY . .
RUN gradle bootJar

FROM openjdk:17 as runtime
WORKDIR /app
ENV PORT 8080
ENV SPRING_PROFILE production
EXPOSE 8080
ARG JAR_FILE=/app/build/libs/*.jar
COPY --from=build ${JAR_FILE} /app/app.jar

RUN chown -R 1000:1000 /app
USER 1000:1000

ENTRYPOINT ["java", "-jar",  "-Dserver.port=${PORT}","-Dspring.profiles.active=${SPRING_PROFILE}", "app.jar"]