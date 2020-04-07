FROM openjdk:8
ADD target/blog-back-end.jar blog-back-end.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "blog-back-end.jar"]