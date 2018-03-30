#!/usr/bin/env bash
set -euo pipefail

cd "$(dirname "$0")/../testkit/src/test/resources/com/hashicorp/nomad/testutils"

rm -f *

# Now we mostly follow the instructions in the "Securing Nomad with TLS" guide at https://www.nomadproject.io/guides/securing-nomad.html

# Generate the CA's private key and certificate
cfssl print-defaults csr | cfssl gencert -initca - | cfssljson -bare nomad-ca

cat >cfssl.json <<'EOF'
{
  "signing": {
    "default": {
      "expiry": "87600h",
      "usages": [
        "signing",
        "key encipherment",
        "server auth",
        "client auth"
      ]
    }
  }
}
EOF

# Generate a certificate for the Nomad server
echo '{}' | cfssl gencert -ca=nomad-ca.pem -ca-key=nomad-ca-key.pem -config=cfssl.json \
    -hostname="server.global.nomad,localhost,127.0.0.1" - | cfssljson -bare server

# Generate a certificate for the Nomad client
echo '{}' | cfssl gencert -ca=nomad-ca.pem -ca-key=nomad-ca-key.pem -config=cfssl.json \
    -hostname="client.global.nomad,localhost,127.0.0.1" - | cfssljson -bare client

# Generate a certificate for the SDK
#
# Note: The instructions in the "Securing Nomad with TLS" guide creates a certificate with no subject names
#       (no `-hostname` flag to the `cfssl gencert` command) for use with the CLI. The JVM considers a certificate with
#       no subject names to be invalid, so we need at least one subject name for the certificate we use with the SKD.
#
echo '{}' | cfssl gencert -ca=nomad-ca.pem -ca-key=nomad-ca-key.pem -profile=client \
    -hostname="localhost,127.0.0.1" - | cfssljson -bare sdk
