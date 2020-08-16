#!/bin/bash
function print(){
  echo "********** ${1} **********"
}
print "clean and build the project"
./gradlew clean build # or use gradle clean build

print "Start minikube"
minikube start
print "Using local docker registry"
eval $(minikube docker-env)

print "Apply deployment and service file for AMQ"
kubectl apply -f kube/demo-amq-deployment.yaml
kubectl apply -f kube/demo-amq-service.yaml

print "Apply deployment and service file for MongoDB"
kubectl apply -f kube/demo-mongo-persistent-claim.yaml
kubectl apply -f kube/demo-mongo-deployment.yaml
kubectl apply -f kube/demo-mongo-service.yaml


print "Building local image"
docker image build -t demo-app .
print "Apply deployment and service file for application"
kubectl apply -f kube/demo-app-deployment.yaml
kubectl apply -f kube/demo-app-service.yaml

