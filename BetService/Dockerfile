FROM anapsix/alpine-java:jre8
ADD target/*.jar /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Xmx192m","-Xss1m","-jar","/app.jar"]
