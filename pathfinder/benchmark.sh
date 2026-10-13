#!/usr/bin/env bash
# Runs the benchmark. Usage:
#   ./benchmark.sh <graph.fmi> <queries.que> <longitude> <latitude> <source-node-id>
# Example: ./benchmark.sh germany.fmi germany.que 9.098 48.746 638394
if [ "$#" -ne 5 ]; then
  echo "usage: ./benchmark.sh <graph.fmi> <queries.que> <longitude> <latitude> <source-node-id>" >&2
  exit 1
fi
# Benchmark.java reads the flags by position, so keep this order.
java -jar target/Benchmark-1.0.0.jar -graph "$1" -lon "$3" -lat "$4" -que "$2" -s "$5"
