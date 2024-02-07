# BEDUproject

PayrollAPI es una herramienta que facilita el registro de empleados en una empresa. Con ella, podemos gestionar la información de los empleados mediante su número de identificación, nombre, apellido, correo electrónico, cargo y departamento. 

## Autores

- [@sglsergiogarcia] (https://github.com/sglsergiogarcia)
- [@emilioenlaluna] (https://github.com/emilioenlaluna)
- [@regieslu] (https://github.com/Regieslu)

## Documentación

[Spring](https://start.spring.io/)

[Swagger](https://swagger.io/)

[Baeldung](https://www.baeldung.com/spring-thymeleaf-error-messages)

[MVNRepository](https://mvnrepository.com/repos/central)

## Instalación 

git init https://github.com/Regieslu/BEDUproject.git

## Ejecución

./gradlew bootRun 

## Application properties

spring.datasource.driver-class-name=org.sqlite.JDBC
spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect
spring.datasource.url=jdbc:sqlite:src/main/resources/database.sqlite
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

## Dependencias

implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
implementation 'org.springframework.boot:spring-boot-starter-web'
implementation 'org.springframework.boot:spring-boot-starter-validation'
implementation 'org.xerial:sqlite-jdbc:3.44.1.0'
implementation 'org.hibernate.orm:hibernate-community-dialects:6.4.0.Final'
compileOnly 'org.projectlombok:lombok'
annotationProcessor 'org.projectlombok:lombok'
implementation 'org.mapstruct:mapstruct:1.5.5.Final'
annotationProcessor 'org.mapstruct:mapstruct-processor:1.5.5.Final'
implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0'
testImplementation 'org.springframework.boot:spring-boot-starter-test'

## Static Analisys Code using Sonar
![image](https://github.com/Regieslu/BEDUproject/assets/74982686/5217c203-2048-47e7-81f2-73464c457745)

## Report 
![image](https://github.com/Regieslu/BEDUproject/assets/74982686/57ecae62-f572-4ef5-bc4a-0405117e2eac)

## Jococo 
![image](https://github.com/Regieslu/BEDUproject/assets/74982686/43a3bc55-c119-49b4-8774-89d3f3d5c13b)


## BEDU 
