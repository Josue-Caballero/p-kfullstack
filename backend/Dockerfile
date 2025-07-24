# Etapa 1: Construcción
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia todo el proyecto y construye el .jar
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final liviana
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copia el .jar generado desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Puerto expuesto por la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
