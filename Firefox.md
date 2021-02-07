# Get rid of new search bar that hides search engines

https://addons.mozilla.org/en-US/firefox/addon/old-search-fixed2

# Reader view disabling in Firefox 60

Force having reader view button:

    mkdir -p ~/.mozilla/chrome
    iv ~/.mozilla/chrome/userChrome.css

Put the contents

```css
@namespace url("http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul");
#reader-mode-button {
	display: -moz-box !important;
	visibility: visible !important;
}
```

cf. https://superuser.com/questions/1113362/is-there-a-way-to-force-enable-reader-view-in-firefox-on-pages-where-the-icon-do/1190577#1190577

# Search bar accidentally turned RTL

This can be restored with `Ctrl`-`Shift`-`X`.
