# Shopify Custom Collections App

An application for Shopify Merchants to view their collections and get more information on the products within those
collections.

Custom Collections Page - > Displays all the custom collections

Collection Details Page -> Displays the products within a specific collection along with the collection image,
                           collection title, product title, product inventory
                            

# Technologies used for the application

## Realm -> Database
   For local database, I used Realm as it stores objects, has ability to create relationships, and very easy to read and write
   a lot of different options. Moreover, Realm has listeners that can be used to get the results of its query or write commands when ran asynchronously which makes the development very maintainable and easy to update

## Retrofit -> REST client for Java and Android
   Retrofit makes it very easy to consume JSON data from a REST based webservice
 
## Gson -> Library to serialize and deserialize JSON to Java objects

## Glide -> Helps in loading and caching images
   Glide is very useful in its various strategies for caching the images with ability to store the original image
   fetched or the one being used by the app or both or neither of them.
   Glide's image caching comes out of the box so never have to worry about it not caching if not told to do so.

<p float="left">
  <img src="https://github.com/VishwaP98/ShopifyCustomCollectionsApp/blob/master/Screen%20Shot%202019-01-21%20at%204.10.36%20AM.png" width="280" height="450" alt="Custom Collections page" hspace="20">

  <img src="https://github.com/VishwaP98/ShopifyCustomCollectionsApp/blob/master/Screen%20Shot%202019-01-21%20at%204.11.06%20AM.png" width="280" height="450" alt="Collection Details page" hspace="20">

  <img src="https://github.com/VishwaP98/ShopifyCustomCollectionsApp/blob/master/Screen%20Shot%202019-01-21%20at%204.11.22%20AM.png" width="280" height="450" alt="Collection Details page" hspace="20">
</p>
