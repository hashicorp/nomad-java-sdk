#!/usr/bin/env bash
set -euo pipefail

cd "$(dirname "$0")/../testkit/src/test/resources/com/hashicorp/nomad/testutils"

rm -f *

# This script is an adaptation of the instructions in the "Securing Nomad with TLS" guide at https://www.nomadproject.io/guides/securing-nomad.html

# Certificate authority CSR config, adapted from the output of `cfssl print-defaults csr`.
# The algorithm has been set to RSA so that the `TlsTest` will run successfully with openjdk 7.
# (Using ecdsa certificates on openjdk 7 results in `java.security.InvalidKeyException: EC parameters error`.)
cat >nomad-ca-csr.json <<'EOF'
{
    "CN": "example.net",
    "hosts": [
        "example.net",
        "www.example.net"
    ],
    "key": {
        "algo": "rsa",
        "size": 2048
    },
    "names": [
        {
            "C": "US",
            "ST": "CA",
            "L": "San Francisco"
        }
    ]
}
EOF

# Generate the CA's private key and certificate
cfssl gencert -initca nomad-ca-csr.json | cfssljson -bare nomad-ca

# Certificate CSR config.
# The algorithm has been set to RSA so that the `TlsTest` will run successfully with openjdk 7.
# (Using ecdsa certificates on openjdk 7 results in `java.security.InvalidKeyException: EC parameters error`.)
cat >csr.json <<'EOF'
{
    "key": {
        "algo": "rsa",
        "size": 2048
    }
}
EOF

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
cfssl gencert -ca=nomad-ca.pem -ca-key=nomad-ca-key.pem -config=cfssl.json \
    -hostname="server.global.nomad,localhost,127.0.0.1" csr.json | cfssljson -bare server

# Generate a certificate for the Nomad client
cfssl gencert -ca=nomad-ca.pem -ca-key=nomad-ca-key.pem -config=cfssl.json \
    -hostname="client.global.nomad,localhost,127.0.0.1" csr.json | cfssljson -bare client

# Generate a certificate for the SDK
#
# Note: The instructions in the "Securing Nomad with TLS" guide creates a certificate with no subject names
#       (no `-hostname` flag to the `cfssl gencert` command) for use with the CLI. The JVM considers a certificate with
#       no subject names to be invalid, so we need at least one subject name for the certificate we use with the SKD.
#
cfssl gencert -ca=nomad-ca.pem -ca-key=nomad-ca-key.pem -profile=client \
    -hostname="localhost,127.0.0.1" csr.json | cfssljson -bare sdk
