FROM maven:3.3.9-jdk-8

ENV JAVA_OPTS=""

EXPOSE 4500

ADD . $MAVEN_HOME

RUN cd $MAVEN_HOME \
 && mvn -B clean package -Dmaven.test.skip\
 && app=$(ls $MAVEN_HOME/target/*.jar | head -1) \
 && mv $app /app.jar \
 && rm -r $MAVEN_HOME

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]
