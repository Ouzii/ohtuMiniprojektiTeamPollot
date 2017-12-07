#!/bin/bash -e

# tama saattaisi auttaa travista asiassa
#shopt -s extglob

: "${BRANCHES_TO_MERGE_REGEX?}" "${BRANCH_TO_MERGE_INTO?}"
: "${GITHUB_SECRET_TOKEN?}" "${GITHUB_REPO?}"

#(: "${BRANCHES_TO_MERGE_REGEX?}")

#(: "${BRANCH_TO_MERGE_INTO?")

#(: "${GITHUB_SECRET_TOKEN?")
#(: "GITHUB_REPO?}")

export GIT_COMMITTER_EMAIL='travis@travis'
export GIT_COMMITTER_NAME='Travis CI'

if ! grep -q "$BRANCHES_TO_MERGE_REGEX" <<< "$TRAVIS_BRANCH"; then
    printf "Current branch %s doesn't match regex %s, exiting\\n" \
        "$TRAVIS_BRANCH" "$BRANCHES_TO_MERGE_REGEX" >&2
    exit 0
fi

# Since Travis does a partial checkout, we need to get the whole thing
repo_temp=$(mktemp -d)
git clone "https://github.com/$GITHUB_REPO" "$repo_temp"

# shellcheck disable=SC2164
cd "$repo_temp"
git checkout master
#git merge $( git log origin/development -1|head -n 1|awk '{print $2}' );
#git pull "https://$GITHUB_SECRET_TOKEN@github.com/$GITHUB_REPO" "$TRAVIS_BRANCH" >/dev/null 2>&1
echo $( git log origin/development -1|head -n 1|awk '{print $2}' );
git log --graph --oneline --decorate --all
#git push -u "https://$GITHUB_SECRET_TOKEN@github.com/$GITHUB_REPO" master >/dev/null 2>&1
