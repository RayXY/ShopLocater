# ShopLocater
An exercise to utilise Spring Boot application to create a HTTP service which allows shop location management and lookup

## User Guide

###Install
1. Compile the source code using Gradle build, you will get a jar "shop-locater-1.0.0.jar"
2. From the directory of the jar run this command: java -jar shop-locater-1.0.0.jar
3. The above command will eventually print "Started Application in [xxx] seconds" to confirm the service is running

###Call the HTTP API
1. To add a shop  
Use your http client to send a POST call to "http://localhost:8080/addshop"  
The body of this POST call should be a JSON matching below format  
{  
    "shopName" : "A Shop Name",  
    "shopAddressNumber" : "A Number",  
    "shopAddressPostCode" : "A Post Code"  
}  
You will be expecting no body in the response but one of three HTTP return status  
    201 Created: if first called to add a shop and the call succeeded  
    409 Conflict: any further call to add shop will recived this status  
    406 Not Acceptable: if the service cannot add the shop because it cannot find its geo code  

2. To find a nearest shop with a geo code  
Use your http client to send a GET call to "http://localhost:8080/findnearest?longitude=[your longitude]&latitude=[your latitude]"  
This call will return a JSON body looks like below  
{  
  "shopIdentity": {  
    "shopName": "A Shop Name",  
    "shopAddressNumber": "A Number",  
    "shopAddressPostCode": "A Post Code"  
  },  
  "shopGeoCode": {  
    "shopLongitude": the shop's longitude,  
    "shopLatitude": the shop's latitude  
  }  
}  

##Some Potential Improvement Area
1. Add authentication
2. Add optimistic concurrent control over accesses
3. Increase test coverage and add integration test.
4. Use proper log

