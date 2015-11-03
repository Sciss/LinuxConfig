# Delete a tag

    git tag -d <tag-name>
    git push origin :refs/tags/<tag-name>

# Store dirty state away, change branch and restore dirty state

    git stash save
    git checkout other-branch
    git merge previous-branch
    git stash pop

