Description
-----------
A really simple service to demonstrate Lightbend's Lagom microservice framework. 

This project exposes an API to add a Saying to a Person resource.

Technology
----------
Lightbend's Lagom framework is used as a platform for building reactive, extremely scalable microservices in Java. Lagom is build on top of Scala, Play and Akka, thus enabling the reactive approach from that ecosystem in Java.

It uses a CQRS / Event Sourcing approach with Cassandra storage (Lagom Persistence) - this is the default standard in the framework. However, you do not have to use this approach e.g. you could just do ordinary CRUD using a Cassandra driver directly.

An early beta version of Lagom is used - so this is not a stable project in terms of API changes. Underneath the covers, Lagom is a wrapper over Play and Akka which are both mature and stable - so in this sense it is production-ready even as a beta. 

Whilst an interesting project to be aware of, there are a few confusing areas and limitations that I hope will be resolved post beta. It is also a highly opinionated framework, so be aware of this if you require flexibility.

Getting Started
----------------

http://www.lagomframework.com/documentation/1.0.x/GettingStarted.html

To run service
--------------

Install activator (at least 1.13.10), then run from the terminal:
```
activator
runAll
```

To use service
--------------

POST /api/person/Bob/saying
```
{"message": "Hello"}
```
e.g:
```
curl -H "Content-Type: application/json" -X POST -d '{"message": "Hello"}' http://localhost:9000/api/person/Bob/saying
```
