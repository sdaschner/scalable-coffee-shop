#!/bin/bash
cd ${0%/*}
set -eu

gradle build
docker build --rm -t localhost:5000/scalable-coffee-shop-barista:1 .
docker run --rm --name barista -p 8003:8080 localhost:5000/scalable-coffee-shop-barista:1
