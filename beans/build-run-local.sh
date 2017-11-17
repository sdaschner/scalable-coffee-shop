#!/bin/bash
cd ${0%/*}
set -eu

gradle build
docker build --rm -t localhost:5000/scalable-coffee-shop-beans:1 .
docker run --rm --name beans -p 8002:8080 localhost:5000/scalable-coffee-shop-beans:1
