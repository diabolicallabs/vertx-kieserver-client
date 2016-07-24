FROM jboss/kie-server:latest

ADD application-roles.properties /opt/jboss/wildfly/standalone/configuration
ADD application-users.properties /opt/jboss/wildfly/standalone/configuration
ADD repository /opt/jboss/.m2/repository

ENV JAVA_OPTS -XX:MaxPermSize=256m -Xms256m -Xmx512m