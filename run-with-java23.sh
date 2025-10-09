#!/bin/bash
# Script to run the application with Java 23

export JAVA_HOME=/Library/Java/JavaVirtualMachines/zulu-23.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH

echo "Using Java version:"
java -version
echo ""

echo "Starting Putting It Together application on port 8686..."
java -jar target/puttingItTogether-0.0.1-SNAPSHOT.jar