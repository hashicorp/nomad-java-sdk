#! /bin/bash
# Generates the API model JavaBeans using reflection on the golang nomad/api package

set -euo pipefail

GO111MODULE=on go run *.go ../sdk/src/main/java/com/hashicorp/nomad/apimodel/
