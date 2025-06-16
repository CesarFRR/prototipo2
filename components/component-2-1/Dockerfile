# ----------- Etapa de compilación -----------
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# Copia solo pom para cache de dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia el código fuente y genera el jar
COPY src ./src
RUN mvn clean package -DskipTests -B

# ----------- Etapa de ejecución -------------
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copia el fat jar desde la etapa de build
COPY --from=build /app/target/component-2-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto por defecto
EXPOSE 8080

# Ejecuta la aplicación
ENTRYPOINT ["java","-jar","app.jar"]
