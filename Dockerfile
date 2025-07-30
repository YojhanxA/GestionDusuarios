# Usamos una imagen base de Java
FROM eclipse-temurin:21-jdk

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo .jar generado (asegúrate que se llame así o ajusta el nombre)
COPY target/gestion-de-usuarios-alcaldia-0.0.1-SNAPSHOT.jar app.jar


# Puerto que vas a exponer
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
