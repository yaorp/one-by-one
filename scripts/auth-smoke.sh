#!/usr/bin/env bash

set -euo pipefail

BASE_URL="${1:-http://localhost:9011}"
USERNAME="${2:-admin}"
PASSWORD="${3:-123456}"
USER_ID="${4:-1}"

echo "==> Login: ${BASE_URL}/auth/login"
LOGIN_RESPONSE="$(curl -sS -X POST "${BASE_URL}/auth/login" \
  -H "Content-Type: application/json" \
  -d "{\"username\":\"${USERNAME}\",\"password\":\"${PASSWORD}\"}")"

echo "${LOGIN_RESPONSE}" | python3 -c 'import json,sys; data=json.load(sys.stdin); print(json.dumps(data, ensure_ascii=False, indent=2))'

TOKEN="$(echo "${LOGIN_RESPONSE}" | python3 -c 'import json,sys; data=json.load(sys.stdin); print((data.get("data") or {}).get("token",""))')"

if [ -z "${TOKEN}" ]; then
  echo "ERROR: 登录失败，未拿到 token。"
  exit 1
fi

echo "==> Access protected API: ${BASE_URL}/user/queryUser?id=${USER_ID}"
PROTECTED_RESPONSE="$(curl -sS -X GET "${BASE_URL}/user/queryUser?id=${USER_ID}" \
  -H "Authorization: Bearer ${TOKEN}")"

echo "${PROTECTED_RESPONSE}" | python3 -c 'import json,sys; data=json.load(sys.stdin); print(json.dumps(data, ensure_ascii=False, indent=2))'
echo "==> Smoke test finished."
