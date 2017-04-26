#!/usr/bin/env bash
set -euo pipefail

[ $# -eq 2 ] || {
  echo "usage: $0 <release-version> <next-working-version>"
  echo
  echo "E.g: $0 0.0.1 0.0.2-SNAPSHOT"
  exit 2
} >&2
release_version="$1"
next_working_version="$2"

[ -z "$(git status --porcelain)" ] || {
  echo "Your working directoy is not pristine"
  echo
  git status
  exit 1
} >&2

mvn versions:set -DgenerateBackupPoms=false -DnewVersion="${release_version}"
git add -u
mvn clean deploy -DperformRelease
git commit -m "Setting version to ${release_version}"
[ -z "$(git status --porcelain)" ] || {
  echo "Your working directory is not pristine after deploying"
  echo
  git status
  exit 1
} &>2
git tag "v${release_version}" -m "Releasing ${release_version}"

mvn versions:set -DgenerateBackupPoms=false -DnewVersion="${next_working_version}"
git add -u
git commit -m "Setting version to ${next_working_version}"
