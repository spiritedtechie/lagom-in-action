## Overview

A really simple service to demonstrate Lightbend's Lagom microservice framework for Java.

This project exposes an API to request a Saying from a Person resource.

## Technology

Lightbend's Lagom framework is used (1.3.3), which is a platform for building reactive, extremely scalable microservices in Java.

- It's build on top of Scala, Play and Akka, thus bringing the reactive approach to Java.
- It uses a CQRS / Event Sourcing approach with Cassandra storage (Lagom Persistence) - this is the default standard in the framework. However, you do not have to use this approach e.g. you could just do ordinary CRUD using a Cassandra driver directly.
- It's also an opinionated framework, so be aware of this if you require flexibility.

## Getting Started

https://www.lagomframework.com/documentation/1.3.x/java/GettingStartedSbt.html

## Setup Intellij

To run the tests from IntelliJ, the Lombok plugin needs to be installed and annotation preprocessing enabled for the project.

## Run Service

Install sbt (at least 1.13.13), then run from the terminal:
```
sbt
runAll
```

To use service
--------------

POST /api/person/Bob/saying
```
{"saying": "Hello"}
```
e.g:
```
curl -H "Content-Type: application/json" -H "Accept: text/plain" -d '{"saying": "Hello"}' http://localhost:9000/api/person/Bob/saying
```
