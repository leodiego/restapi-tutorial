#!/bin/sh
curl -i -X POST -H 'Content-Type: application/json'  -d '{"name":"John Doe", "age":38}' http://localhost:8080/api/person

