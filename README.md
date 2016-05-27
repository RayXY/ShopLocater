# ShopLocater
An exercise to utilise Spring Boot application to create a REST service which allows place location management and lookup

## User Guide

###Install
1. Compile the source code using Gradle build, you will get a jar "place-locater-1.0.0.jar"
2. From the directory of the jar run this command: java -jar place-locater-1.0.0.jar
3. The above command will eventually print "Started Application in [xxx] seconds" to confirm the service is running

###Call the REST API
1. To add a place
Use your http client to send a POST call to "http://localhost:8080/addshop"  
The body of this POST call should be a JSON matching below format  
{  
    "name" : "A Shop Name",
    "addressNumber" : "A Number",
    "postCode" : "A Post Code"
}  
You will be expecting no body in the response but one of three HTTP return status  
    201 Created: if first called to add a place and the call succeeded
    409 Conflict: any further call to add place will recived this status
    406 Not Acceptable: if the service cannot add the place because it cannot find its geo code

2. To find a nearest place with a geo code
Use your http client to send a GET call to "http://localhost:8080/findnearest?longitude=[your longitude]&latitude=[your latitude]"  
This call will return a JSON body looks like below  
{  
  "placeIdentity": {
    "name": "A Shop Name",
    "addressNumber": "A Number",
    "postCode": "A Post Code"
  },  
  "placeGeoCode": {
    "longitude": the place's longitude,
    "latitude": the place's latitude
  }  
}  

##Some Potential Improvement Area
1. Add authentication
2. Add optimistic concurrent control over accesses
3. Increase test coverage and add integration test.
4. Use proper log

