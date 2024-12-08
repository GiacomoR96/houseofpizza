
FROM registry.access.redhat.com/ubi8/openjdk-17-runtime:1.19-1

ARG SOURCE_JAR=./houseofpizza/houseofpizza/target
ENV JAR_PATH $SOURCE_JAR

COPY ${JAR_PATH}/*.jar /tmp/app.jar
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /tmp/app.jar"]