# Renewing expiry of keys

Enter the GPG shell for your key

    gpg --edit-key contact@sciss.de

It should say that a secret key is available. It will list `sec` and encryption sub-key `ssb`. By default the main key is selected (`key 0`).
Change expiry typing `expire`, then typing the duration from today, e.g. `2y` to let it expire in two years time. Change to the relevant sub-keys
(e.g. `key 1`), and repeat. Finally save and exit via `save`.

See https://www.g-loaded.eu/2010/11/01/change-expiration-date-gpg-key/

