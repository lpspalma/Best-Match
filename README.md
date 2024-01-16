# Best-Match
Find the best-matched restaurants Application

Basic search function that allows search restaurants.
### The search is based on five criteria:
  ##### Restaurant Name,
  ##### Customer Rating(1 star ~ 5 stars),
  ##### Distance(1 mile ~ 10 miles),
  ##### Price(how much one person will spend on average, $10 ~ $50),
  ##### Cuisine(Chinese, American, Thai, etc.).

# HOW TO TEST
To use the app you should
The application is using Java 17, make sure that you have this version installed
https://docs.oracle.com/en/java/javase/17/install/installation-jdk-microsoft-windows-platforms.html#GUID-704F1FB7-802A-43C3-9220-6F8036CD0E5A
1. Run the application locally
i. Using IDE - IntelliJ or Eclipse
ii. Command Line
java -jar <path where is the jar>\BestMatch-0.0.1-SNAPSHOT.jar
2. In a REST app like Postman, SoapUI, add the URL: http://localhost:8080/findMatch to do a POST request
3. In the Body space, add the five criteria that the user want to filter, in Json format.
```json
{
"restaurantName": ""
"rating": "",
"distance": "",
"price": "",
"cuisine": ""
}
```
No need to fill all criteria, if the value is EMPTY the app will understand that you don't have any preference
for these criteria.

4. Click on the button Send to send the request

5. The application will return a result
 
  Types of results expected:
    
    i. 200 - Success - Found Matches
      When the app find a restaurant that matches with the criteria added by the user, then the response
      will be Status 200 and a list of Restaurants ordered by Distance from the user, Rating and Price.
      Maximum 5 Restaurants in the response list.
   
    ii. 200 - Success - Not found any matches
      When the app did not find any matches that meets the search criteria, then the response will be Status
      200 but with an empty list.
    
    iii. 400 - Bad Request
      When the user doesn't respect the format of the fields then the response will be a list with all the
      errors found.
    
    iv. 500 - Server Error
      The application did not start properly.

  All the data is already added in the DataBase, so, there are no need to upload any file in application.
  But if there are any need to update the location of the file restaurant.csv and cuisine.csv, them you can go to
  application.properties and change the path for these files.
  If add or remove items in the csv files, them restart the application, that the Data Base will be
  updated automatically.
