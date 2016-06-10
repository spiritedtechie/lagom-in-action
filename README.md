A really simple project to demonstrate Lightbend's Lagom framework. 

This project exposes an API to add a Saying to a Person resource.

It uses the standard CQRS / Event Sourcing approach with Cassandra storage - this is the default standard in the framework.

Getting Started:

http://www.lagomframework.com/documentation/1.0.x/GettingStarted.html

To run:

Install activator (at least 1.13.10), then run from the terminal:

  activator

  runAll

To use service:

POST /api/person/Bob/saying

{"message": "Hello"}

e.g:

  curl -H "Content-Type: application/json" -X POST -d '{"message": "Hello"}' http://localhost:9000/api/person/Bob/saying
