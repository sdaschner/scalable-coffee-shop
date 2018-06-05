#!/bin/bash
cd ${0%/*}
set -eu

gradle build
docker build --rm -t scalable-coffee-shop-orders:1 .
docker run --rm --name orders -p 8001:8080 scalable-coffee-shop-orders:1
