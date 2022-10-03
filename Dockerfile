FROM openjdk:11

COPY target/AHG-*.jar app.jar

CMD ["java" , "-jar" , "app.jar" ]
