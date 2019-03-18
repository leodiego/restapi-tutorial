#!/bin/sh
curl -i -X PUT -H 'Content-Type: application/json'  -d '{"name":"Bob Lowe", "age":60}' http://localhost:8080/api/person/2


