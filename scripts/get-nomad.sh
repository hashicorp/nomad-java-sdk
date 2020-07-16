#!/usr/bin/env bash
set -euo pipefail

nomad_version="0.12.0+ent"
go_version=1.14.5

cd "$(dirname "$0")/.."

[ -n "${GOPATH:-}" ] || {
    echo "$0: error: GOPATH must be set" >&2
    exit 1
}

echo "Getting Go ${go_version}…"
eval "$(curl -sL https://raw.githubusercontent.com/travis-ci/gimme/master/gimme | GIMME_GO_VERSION="${go_version}" bash)"

function expected_nomad_is_available {
    hash nomad 2>/dev/null && [[ "$(nomad version)" == Nomad\ *"${nomad_version}"* ]]
}

if expected_nomad_is_available; then
    echo "Nomad ${nomad_version} already available"
    exit
fi

echo "Downloading Nomad ${nomad_version}"
curl -sL https://releases.hashicorp.com/nomad/${nomad_version}/nomad_${nomad_version}_linux_amd64.zip -o nomad.zip
sudo unzip -o nomad.zip -d /usr/local/bin
sudo chmod 0755 /usr/local/bin/nomad
sudo chown root:root /usr/local/bin/nomad

expected_nomad_is_available || {
    echo "$0: error: Nomad ${nomad_version} is not available even after provisioning" >&2
    exit 1
}

echo "Nomad ${nomad_version} now available"
