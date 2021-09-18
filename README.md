![icon](screenshots/screenshot_icon.png)

# HyBlockUtils: An Android App for Hypixel SkyBlock
An Android app containing a collection of useful tools for Hypixel SkyBlock

![icon](screenshots/screenshot_app_pages.png)

## Pages
- **Main Menu**
- **Profile Viewer**  - personal and search
- **Calender**        - showing upcoming events in the next 48 hours in two columns
- **Notifications**   - for next Dark Auction and other important events like Spooky Festival, etc.
- **Bazaar**          - list of all items and prices sorted by category name
- **AH Price Lookup** - search through all ah items names and lore using space separated keywords; double quotes for grouping
- **Settings**        - API key, personal user name and an option to limit the ah page requests to 10 instead of the full 70

## First Steps
1. **API Key**            - look up your hypixel api key or enter `/api` in-game to create a new one
2. **API Settings**       - go to the gear icon in the top right corner and enter your API key
3. **User Name Setting**  - go to the gear icon in the top right corner and enter your or any other user name (used for the personal Profile Viewer)
4. Done

## Requirements
- Most versions of Android (API level 16 and higher)
    - tested with API level 16-emulated (Jelly Bean 4.1), 17-emulated (Jelly Bean 4.2), 22-emulated (Lollipop 5.1), 23-physical (Marshmallow 6.0), 25-emulated (Nougat 7.1) and 30-physical (Android 11)
- about 150 MB of RAM 
    - maximum usage for an empty ah query -> all ah items in memory
- internet connection 
    - the faster the better, preferably use W-Lan because 70 ah pages are about 120 MB of data volume
- some CPU 
    - again - the faster the better

## Credits
TheNullicorn - for this amazing NBT parsing lib https://github.com/TheNullicorn/Nedit
