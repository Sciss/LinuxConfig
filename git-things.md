# Delete a tag

    git tag -d <tag-name>
    git push origin :refs/tags/<tag-name>

# Store dirty state away, change branch and restore dirty state

Assuming that we work on `<previous-branch>`, and we want to
bring `<other-branch>` up-to-date and keep working there, but
there is already dirty state in `<previous-branch>`:

    git stash save
    git checkout <other-branch>
    git merge <previous-branch>
    git stash pop

