FROM eclipse-temurin:17-jdk-alpine
LABEL authors="trytytus"

WORKDIR /app

ENTRYPOINT ["top", "-b"]