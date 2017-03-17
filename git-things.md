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

# Include a full copy of a symlink outside the main directory

E.g. for linking in `all.bib` bibliography that actually sits in its own git repository.

- Change from symlink `ln -s` to a "hard link" `ln`. That way git will push the full contents of the thus linked file

# Search _in code_ on GitHub

Make sure the query type is `Code`, e.g.

- https://github.com/search?utf8=%E2%9C%93&q=user%3Asciss+%22mfcc%22&type=Code&ref=advsearch&l=&l=

