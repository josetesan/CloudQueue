FROM anapsix/alpine-java:jre8
ADD target/*.jar /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","--add-modules","java.xml.bind","javax.transaction","-Djava.security.egd=file:/dev/./urandom","-Xmx192m","-Xss1m","-jar","/app.jar"]
