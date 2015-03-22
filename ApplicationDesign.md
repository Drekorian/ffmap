# Basics #

## Architecture ##

Our aim is to create ffmap as a MVC architecture application. Since native XML database is not particularly widespread technology, we are currently seeking a Java web framework to support our vision. We are currently considering involvement of the Play framework.

### MVC architecture ###

In order to maximally use the advantages of XML technologies, our goal is to create special Object relational mapping (ORM) to parse data stored in native XML database. In order to both increase performance and simplify creating of the viewers our intention is to parse the XML data from the database only partially -- while letting the mapped object to hold its raw XML content.

This will allow us to prepare the data for the viewer using the XSLT transformations which we hope to prove being more effective than using JSP or similar technology.

## Interface ##

ffmap will support both web RESTful interface and console interface.

The RESTful interface is going to use the Google maps API to visualize the requested information. Web interface is primarily focused on desktop web browsers. Due to time reasons no mobile devices support is planned so far.

While the main purpose of the console interface is testing and debugging, it can also be used by people without access to graphics display.

# Data #

ffmap's data are going to be stored in XML database. For that purpose we've chosen open source BaseX database. Wr have chosen BaseX mainly because of being accessible via different protocols and useful GUI front-end tool.

## Data flow ##

![http://img713.imageshack.us/img713/3692/ffmapdata.png](http://img713.imageshack.us/img713/3692/ffmapdata.png)

### List of used 3<sup>rd</sup> party SW, libraries and technologies ###

  * BaseX
  * Google Maps API
  * Java SE 6.0
  * XML
  * XML Schema
  * XSLT
  * Xerces