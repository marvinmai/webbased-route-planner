#!/usr/bin/env bash
# Starts the web app (backend + frontend) on http://localhost:8083.
# Usage: ./start.sh <path-to-germany.fmi>
GRAPH_FILE="${1:?usage: ./start.sh <path-to-germany.fmi>}"
java -jar -Xmx8G webapp/target/webapp-1.0.0.jar "$GRAPH_FILE"
