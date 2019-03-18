#!/bin/sh
curl -i -X POST -H 'Content-Type: application/json'  -d '{"name":"Choke Yee", "age":18}' http://localhost:8080/api/person

