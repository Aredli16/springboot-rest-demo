#!/bin/sh

docker tag book devopsexampletestcontainerregistry.azurecr.io/book:latest
docker tag book devopsexampletestcontainerregistry.azurecr.io/book:1.0.0-SNAPSHOT

docker push devopsexampletestcontainerregistry.azurecr.io/book --all-tags
