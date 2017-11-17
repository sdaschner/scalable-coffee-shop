FROM jboss/wildfly:11.0.0.Final

RUN mkdir -p /opt/jboss/wildfly/modules/javax/json/bind/main/
COPY modules/jsonb/* /opt/jboss/wildfly/modules/javax/json/bind/main/

RUN mkdir -p /opt/jboss/wildfly/modules/org/apache/kafka/clients/main/
COPY modules/kafka/* /opt/jboss/wildfly/modules/org/apache/kafka/clients/main/

COPY standalone.xml /opt/jboss/wildfly/standalone/configuration/
