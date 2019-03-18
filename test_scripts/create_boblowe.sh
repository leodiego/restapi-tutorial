#!/bin/sh
curl -i -X POST -H 'Content-Type: application/json'  -d '{"name":"Bob Lowe", "age":43}' http://localhost:8080/api/person

