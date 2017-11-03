#! /bin/bash
# Generates the API model JavaBeans using reflection on the golang nomad/api package

set -euo pipefail

# move to the root directory of the repository
cd "$(dirname "$0")/.."

go run generator/*.go sdk/src/main/java/com/hashicorp/nomad/apimodel/
