FROM anapsix/alpine-java:9
ADD target/*.jar /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","--add-modules","java.xml.bind","-Djava.security.egd=file:/dev/./urandom","-Xmx192m","-Xss1m","-jar","/app.jar"]
