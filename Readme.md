# Demo application
This demo project show how to setup a Spring Boot microservice with ApacheMQ and Mongo DB
## Technologies and tools
Following technologies and tools are being used in this application
* Spring Boot
* Apache Active MQ
* Mongo DB
* Docker
* Docker Compose
* Gradle

## Spring Boot demo application
This project is a Spring Boot application that uses Mongo DB and Apache Active MQ.
The application has five controllers:
### AmqController
This controller has one method which is called send. Use it to send a message to inbound queue.
###InboundQueueListenerController
This controller listens to inbound queue, when it receives a message it updates the relevant mongo db document in database and send the updated message to outbound queue
###OutboundQueueListenerController
This controller listens to outbound queue, currently it does nothing but logging information about received message.
###MongoCrudController
It is a simple crud controller for mongo db MongoDocument document. the sending and receiving data format i s JSON
use postman collection to populate the demo database with some documents.
* Get all documents: HTTP Method: GET  url http://{host:port}/api/documents
* Get a document with id: HTTP Method: GET  url http://{host:port}/api/documents/{id}
* Create a new document: HTTP Method: POST  url http://{host:port}/api/documents
* Update a document: HTTP Method: PUT  url http://{host:port}/api/documents
* Delete a document: HTTP Method: DELETE  url http://{host:port}/api/documents/{id}


###StatusController
It is a simple ping controller : http://{host:port}/ping should return OK


## Running the application without using kubernetes:
It is possible to run the application without using kubernetes deploy files.
docker and docker compose are required to run the application locally.
The docker and docker compose files are in the root directory of the application. 
* Uncomment the commented line in docker file (EXPOSE : 8080)
* Open a terminal and go to root directory of the project.
* execute "./gradlew clean  build"
* execute docker-compose up -d 
The application runs on port 8080. Change the port to desired one in docker  filer
make sure the containers demo-app, demo-mongo and demo-amq are up and running by executing "docker ps -a" 

## Running the application on minikube
To run the application on minikube use run-on-minikube.sh.
The shell script clean and build the project, start minikube and apply the required kuberenetes  files.
when all services deployed then use "minikube service demo-app" to find the host ip and node port to call the services.
Three services should start. demo-app, demo-mongo and demo-amq
## Test
##Integration test
Not implemented yet.
##Unit test
Not implemented yet. 
### Test with postman
There is a postman collection in postman folder in the project use it to 
send rest calls to MongoCrudController

# Improvments
* Eliminating code duplication by creating a base class for Message and MongoDocumentCommand and MongoDocument

#Command list
* eval $(minikube docker-env)
* docker image build -t demo-app . 
* kubectl apply -f kube

* minikube delete
* minikube start

* list service : kubectl get services 
* list pods : kubectl get pods
* se log s kubectl logs <pod id>
* minikube service <service-name>
* minikube dashboard

# links
https://phoenixnap.com/kb/install-minikube-on-ubuntu
