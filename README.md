# Crossword Scraper
[![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/jpd236/CrosswordScraper/gradle-build.yaml?branch=master)](https://github.com/jpd236/CrosswordScraper/actions/workflows/gradle-build.yaml)

Browser extension which downloads crosswords from crossword applets for offline solving.

## Install
- [Mozilla Firefox](https://addons.mozilla.org/en-US/firefox/addon/crossword-scraper/)
- [Google Chrome](https://chrome.google.com/webstore/detail/crossword-scraper/lmneijnoafbpnfdjabialjehgohpmcpo)

## Development
Crossword Scraper is built on Kotlin/JS. To build and run the extension locally, run:

`./gradlew developmentChromeExtension`

then install the extension from the `build/extension/developmentChrome` directory.

Replace "Chrome" with "Firefox" for use with Firefox.

Most of the actual puzzle format conversion uses the [kotwords](https://github.com/jpd236/kotwords) library.
