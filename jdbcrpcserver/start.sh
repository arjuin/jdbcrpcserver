#!/bin/sh

JAVA_HOME='/usr/lib/jvm/jre-1.8.0/bin'

$JAVA_HOME/java -cp /root/JDBCRPCServer/JDBCRPCServer.jar:/root/JDBCRPCServer/lib/*:/usr/hdp/3.2.4.2-2/hbase/lib/*:/usr/hdp/2.2.4.2-2/phoenix/*  -Dlog4j.configuration=file:/root/JDBCRPCServer/log4j.properties jdbcrpc.server.Server