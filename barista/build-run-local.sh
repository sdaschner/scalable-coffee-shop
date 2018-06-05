#!/bin/bash
cd ${0%/*}
set -eu

gradle build
docker build --rm -t scalable-coffee-shop-barista:1 .
docker run --rm --name barista -p 8003:8080 scalable-coffee-shop-barista:1
