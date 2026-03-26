# evintr-old

The original version of **evintr** — an event discovery app built around 2012–2014. You entered a zip code and event category, and it would surface nearby events. This repo is the archived codebase before any major rewrites.

## What It Was

Evintr (the "event engine") had two parts: an Android app and a PHP web backend. The idea was simple — filter events by type and location. The Android side let you pick an event category from a dropdown (with subcategories), enter a zip code, and browse results. The web frontend did the same thing in a browser.

The backend used a PHP API to query event data and return results to both the Android app and web clients. There was Facebook login integration on the Android side.

## Architecture

```
evintr-old/
├── android/           # Android app (Eclipse project structure)
│   └── src/com/evintr/evintr/
│       ├── EvintrActivity.java    # Main screen — category/zip search
│       ├── ResultActivity.java    # Browse search results
│       ├── AddActivity.java       # Add a new event
│       ├── PageActivity.java      # Individual event detail
│       ├── FacebookActivity.java  # Facebook login
│       └── DateTimeDialog.java    # Date/time picker dialog
└── web/               # PHP web frontend
    ├── homepage.html  # Landing page
    ├── api.php        # Main API endpoint
    ├── droidapi.php   # Android-specific API
    ├── add.php        # Add event endpoint
    └── nearby.js      # Client-side location helpers
```

## Notes

This is an old project — Eclipse project structure, classic Android SDK, jQuery on the web side. The Google Analytics tracking ID (`UA-32170936-1`) in the HTML puts it squarely in the early 2010s. The "old" in the repo name means a newer version of evintr exists somewhere — this one is the historical record.
