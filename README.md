A really simple project to demonstrate Lightbend's Lagom framework. 

This project exposes an API to add a saying to a Person resource.

e.g. POST /api/person/Bob/saying

It uses the standard CQRS / Event Sourcing approach with Cassandra storage - this is the default standard in the framework.
