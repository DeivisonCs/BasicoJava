# Use a imagem do JDK como base
FROM openjdk:21-jdk-slim

RUN adduser --system spring && adduser --group spring

USER spring:spring

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo JAR da build do Gradle
COPY build/libs/*.jar app.jar

# Exponha a porta que sua aplicação usa
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

