# Usa una imagen base de Java
FROM openjdk:21-ea-24-oracle

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR de tu aplicación al contenedor
COPY target/mi-microservicio-1.0-SNAPSHOT.jar app.jar

# Copia la wallet de Oracle al contenedor
COPY Wallet_dbFullstack3 /app/oracle_wallet/

# Expone el puerto en el que tu aplicación escuchará
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]