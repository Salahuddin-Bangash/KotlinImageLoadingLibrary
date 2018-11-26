# Mindvalley Assessment Test

# Task

Imagine you are on the Pinterest Android team and you are working with some colleagues on the pinboard (the scrolling wall of images), you split up the tasks among each other and your task is to create an image loading library that will be used to asynchronously download the images for the pins on the pinboard when they are needed.


# Requirement

    1. Use the following url for loading data: http://pastebin.com/raw/wgkJgazE
    2. Images and JSON should be cached efficiently (in-memory only, no need for caching to disk);
    3. The cache should have a configurable max capacity and should evict images not recently used;
    4. An image load may be cancelled;
    5. The same image may be requested by multiple sources simultaneously (even before it has loaded), and if one of the sources cancels the load, it should not affect the remaining requests;
    6. Multiple distinct resources may be requested in parallel;
    7. You can work under the assumption that the same URL will always return the same resource;
    8. The library should be easy to integrate into new Android project / apps;
    9. You are supposed to build a solid structure and use the needed programming design patterns;
    10. Think that the list of item returned by the API can reach 100 items or even more. At a time, you should only load 10 items, and load more from the API when the user reach the end of the list;
    11. Usage of Material Design UI elements (Ripple, Fab button, Animations) is an advantage;
    12. Adding "pull to refresh" is an advantage.
    
    
# Architecture
MVP(Model-View-Presenter)

# Library Used
1. butterknife
2. gson
3. loopj
4. Custom Image load library

