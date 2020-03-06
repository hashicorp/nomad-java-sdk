#!/usr/bin/env bash
set -euo pipefail

nomad_version=v0.9.7
go_version=1.11

cd "$(dirname "$0")/.."

[ -n "${GOPATH:-}" ] || {
    echo "$0: error: GOPATH must be set" >&2
    exit 1
}

function expected_nomad_is_available {
    hash nomad 2>/dev/null && [[ "$(nomad version)" == Nomad\ *"${nomad_version}"* ]]
}

if expected_nomad_is_available; then
    echo "Nomad ${nomad_version} already available"
    exit
fi

echo "Will clone Nomad ${nomad_version} and build it with Go ${go_version}"

echo "Cloning Nomad ${nomad_version}…"
import_path=github.com/hashicorp/nomad
worktree="$GOPATH/src/${import_path}"
rm -rf "${worktree}"
git clone --depth 1 --branch "${nomad_version}" "https://${import_path}" "${worktree}"

echo "Getting Go ${go_version}…"
eval "$(curl -sL https://raw.githubusercontent.com/travis-ci/gimme/master/gimme | GIMME_GO_VERSION="${go_version}" bash)"

echo "Building Nomad…"
go install -tags nomad_test "${import_path}"

expected_nomad_is_available || {
    echo "$0: error: Nomad ${nomad_version} is not available even after provisioning" >&2
    exit 1
}

echo "Nomad ${nomad_version} now available"
