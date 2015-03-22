# Overview #

**NEW:** [Intended application design](ApplicationDesign.md)

Ffmap is a semestral project for PB138 - Modern Markup Languages class at [Masaryk University](http://www.muni.cz/?lang=en), [Faculty of Informatics](http://www.fi.muni.cz/index.xhtml.en). Currently under development, it uses [JAVA SE](http://en.wikipedia.org/wiki/Java_se), [XML](http://en.wikipedia.org/wiki/Xml), [XHTML](http://en.wikipedia.org/wiki/Xhtml) and [Google Maps API](http://en.wikipedia.org/wiki/Google_Maps_API#Google_Maps_API).

The main goal of our project is to create a user-friendly, easy-to-use web database of fast-food joints in [Brno](http://en.wikipedia.org/wiki/Brno) (or any other city for that matter). Our application will allow users to search, add and update fast-food joints, based on their location, menu, opening hours and various other criteria. Using the power of [Google Maps API](http://en.wikipedia.org/wiki/Google_Maps_API#Google_Maps_API), users will be able to locate the closest source of food whenever the need arises.


# Detailed description #

## Used technologies ##

Ffmap is based on JAVA SE version 6.0 for application logic, XHTML for the front-end part and XML for storing data. It also makes heavy use of the aforementioned Google Maps API for easily readable presentation of query results.

## Capabilities ##

Ffmap is aimed at storing and presenting information about fast-food joints in Brno. It allows it's users to add, update and search it's database and presents the results as locations on a map.

Ffmap stores information about fast-food joints, more specifically location, menu, opening hours and prices, which can be adjusted as time passes. Based on these items, users can search the database using criteria such as:
  * Distance from a present/given location
  * Currently open joints
  * Menu items
  * Prices
and others, or their combinations.
The application then displays the results as flags with textual description on the famous Google Maps for ease of use.

As the data stored need to be adjusted as time flows, registered users are allowed to add new items to the database and update existing. Deleting entries is reserved to site administrators.

Currently ffmap is developed purely as a web-based service. If time allows however, support for IPhone and Android is possible.

## Development team ##

### Developers ###
  * [Marek Osvald](MarekOsvald.md)
  * [Martin Putniorz](MartinPutniorz.md)
  * [Alexandar Živkovič](AlexandarZivkovic.md)

### Supervisors ###
  * Mgr. Marek Grác
  * Mgr. Luděk Bártek, Ph.D.
  * doc. RNDr. Tomáš Pitner, Ph.D.