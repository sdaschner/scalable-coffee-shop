#!/bin/bash
cd ${0%/*}
set -eu

gradle build
docker build --rm -t scalable-coffee-shop-beans:1 .
docker run --rm --name beans -p 8002:8080 scalable-coffee-shop-beans:1
