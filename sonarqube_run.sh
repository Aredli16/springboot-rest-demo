#!/bin/sh

docker run -d --name sonarqube -p 8000:9000 sonarqube:latest

mvn clean verify sonar:sonar \
  -Dsonar.projectKey=book \
  -Dsonar.projectName='book' \
  -Dsonar.host.url=http://localhost:8000 \
  -Dsonar.token=sqp_ed1a6630e444df9e1f148a68bc62fac848ce0e9b
