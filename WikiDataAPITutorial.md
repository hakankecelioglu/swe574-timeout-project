# WikiData APIs for Semantic Tagging #

Wikidata is a free linked database that can be read and edited by both humans and machines.
Wikidata acts as central storage for the structured data of its Wikimedia sister projects including Wikipedia, Wikivoyage, Wikisource, and others.

Wikidata keeps structured data in a 2d-space with a specific distance between them. In order find related items of a specific tag item, a query must be executed on the database.

WikiData has query APIs to extract meaningful information from database
https://wdq.wmflabs.org/api_documentation.html

"Tree" type of query API must be used to find related tags of a specific tag.

# API #

tree[ITEM,...][PROPERTY,...][PROPERTY,...]

This returns all items on a tree of properties (can be separated by commas), following properties recursively from root items.

The first list (one or multiple ITEM) are the root elements of the tree.

The second list (one or multiple PROPERTY, can be empty) is the list of properties to follow forward; that is, if root item R has a claim P:I, and P is in the list, the search will branch recursively to item I as well.

An example would be to follow a taxonomy, starting from a species.

The third list (one or multiple PROPERTY, can be empty, or omitted entirely) is the list of properties to follow in reverse; that is, if (for a root item R) an item I has a claim P:R, and P is in the list, the search will branch recursively to item I as well.

An example would be to find all places in a country by following the "in administrative unit" trail in reverse, starting form the country.

Example: " tree[30](30.md)[150](150.md)[17,131] " returns all items that are in the country (P17) or administrative unit (P131) of the U.S.A. (Q30), or sub-units thereof (P150).

# Example API Usage #

All animals on Wikidata
https://wdq.wmflabs.org/api?q=tree[729][150][171,273,75,76,77,70,71,74,89]

# Semantic Tagging Query API #

First, when the user enters the word, following api is called:

https://www.wikidata.org/w/api.php?action=wbgetentities&sites=enwiki&titles=cat&normalize=&format=json

It returns all the items that contains the word cat in their title with their descriptions. These items are presented to user to make a selection.

For example for cat word, following items are returned by API:

cat(Q146) : domesticated species of feline

cat(Q300918) : Unix utility that concatenates and lists files

Cat(Q5050998) : fictional character in Red Dwarf

When the user selects the desired item, tag is kept in database with this item id, and url to that item, that is: https://www.wikidata.org/wiki/Q146

In search and recommendation functions semantic search query will be executed based on the item id's of the related group or event.

If a group has an tag with item id 146 (cat - Q146 : domesticated species of feline) following query will be executed to get similar items;

https://wdq.wmflabs.org/api?q=tree[146][279]ORtree[146][31]

The list of wikidata properties can be accessed on:
http://www.wikidata.org/wiki/Wikidata:List_of_properties/Generic

For semantic search query, instance of(279) and subclass of(31) properties are used to find the related tags of the given tag. In data tree, root and child properties are returned and used for semantic tagging searchs in the project.

The query returns id of related elements which is

animal(Q729) : multicellular eukaryotic organisms

The search in database is executed for groups and events that has id Q729 in their tag list and provide association for recommendation and search functions