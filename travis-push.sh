#!/bin/sh

setup_git() {
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "Travis CI"
}

commit_website_files() {
  git add -A
  git commit --message "Travis build: $TRAVIS_BUILD_NUMBER"
}

upload_files() {
  git remote add origin https://${GH_TOKEN}@github.com/Ouzii/ohtuMiniprojektiTeamPollot.git > /dev/null 2>&1
  git push --set-upstream origin development:master
}


setup_git
commit_website_files
upload_files
