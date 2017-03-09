# Notes on migrating to the new laptop with Debian Stretch

## Migrating GPG

- See https://www.phildev.net/pgp/gpg_moving_keys.html
- Start `sshd` on old machine
- Use `scp -pr` to copy `~/.gnupg` to the new computer
- That's it. `sudo apt install enigmail` for Icedove

## Git-Aware-Prompt

- `cd ~/Documents/devel/`
- `git clone git@github.com:Sciss/git-aware-prompt.git`
- `vi ~/.bashrc`

And append these lines:

```bash
export GITAWAREPROMPT=~/Documents/devel/git-aware-prompt
source "${GITAWAREPROMPT}/main.sh"
export PS1="\${debian_chroot:+(\$debian_chroot)}\u@\h:\w \[$txtcyn\]\$git_branch\[$txtred\]\$git_dirty\[$txtrst\]\$ "
```

## Firefox

- Add-ons: uBlock Origin, Cookie Monster, Color Transform
- Preferences > Privacy > History. Select 'Firefox will: Use custom settings for history', uncheck 'Accept cookies from sites'
  (these will have to be enabled for each site using Cookie Monster)

## Configure git

```bash
git config --global core.editor "vim"
git config --global user.name "Hanns Holger Rutz"
git config --global user.email "contact@sciss.de"
```

## Sane Intl Formats

- Edit `/etc/locale.gen` and enable `en_DK.UTF-8 UTF-8`
- Run `locale-gen`
- Open Gnome 'Region & Language' settings

