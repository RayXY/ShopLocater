# ShopLocater
An exercise to utilise Spring Boot application to create a HTTP service which allows shop location management and lookup

## User Guide

## Install
1. Compile the source code using Gradle build, you will get a jar "shop-locater-1.0.0.jar"
2. From the directory of the jar run this command: java -jar shop-locater-1.0.0.jar
3. The above command will eventually print "Started Application in [xxx] seconds" to confirm the service is running

### Call the HTTP API
While the server is running, use your http client to access below API with start with "http://localhost:8080"
### Manage user defined places with RESTful API
Users are allowed to add and update and delete their own places by using the API.

#### Add/Update a Place
Description: Add/Update a user defined place. This will then be searchable by the Location Explorer API  
Method URL: /rest/userPlace  
Method Type: PUT  
Request Body:  

> {
	"name" : "A Shop Name",  
	"addressNumber" : "A Number",  
	"postCode" : "A Post Code",  
	“type”: “A place type”   
}

Response: Header only indicate the status of OK/Not Accepted  

#### Delete a Place
Description: Delete a place from the user defined places.   
Method URL: /rest/userPlace  
Method Type: DELETE  
Request Body:  

> {   
> "name" : "A Shop Name",   
> "addressNumber" : "A Number",   
> "postCode" : "A Post Code"   
> }  

Response: Header indicates the status of the action and the status is always OK   

#### Get a user defined place details
Description: Find a user defined place.   
Method URL: /rest/userPlace?name=xxx&number=xxx&postcode=xxx  
Method Type: Get  
Request Body: NA  
Response: Header only indicate the status of the action and the status is always OK or Not Found. If OK should also return a response body.  

> {  
"PlaceIdentity": {  
"name" : "A Shop Name",  
"addressNumber" : "A Number",  
"postCode" : "A Post Code",  
“type”: “A place type”  
},  
"PlaceGeoCode": {   
"shopLongitude": the shop's longitude,  
"shopLatitude": the shop's latitude  
}  
}  

#### Get all user defined Places
Description: Find a user defined place.   
Method URL: /rest/userPlaces  
Method Type: Get  
Request Body: NA  
Response: Header only indicate the status of the action and the status is always OK and also return a response body.  

> {  
“Places”:  
[  
placeIdentity1 as string,  
placeIdentity1 as string,  
......  
]  
}  

### Do places searches with RPC style API
####Find nearest place to a geo location
Description: Find the nearest shop with a geo code  
Method URL: rpc/findnearest  
Method Type: Post  
Request Body:   

> {  
"shopLongitude": the shop's longitude,  
"shopLatitude": the shop's latitude  
}  

Response: Header only indicate the status of the action and the status is always OK and also return a response body.  

> {  
"PlaceIdentity": {  
"name" : "A Shop Name",  
"addressNumber" : "A Number",  
"postCode" : "A Post Code",  
“type”: “A place type”  
},  
"PlaceGeoCode": {  
"shopLongitude": the shop's longitude,  
"shopLatitude": the shop's latitude  
}  
}  

####Find the meeting point
Description: Provide a list of location, find a place which cost the least to travel to from each location individually.   
Method URL: rpc/findmeetingpoint  
Method Type: Post  
Request Body:   

> {  
"type": {type}  
"origins": [  
"{postCode1}",  
"{postCode2}",
......   
]  
}  

Response Header: only indicate the status of the action and the status is always OK and also Response Body in case of success:  

> {  
"PlaceIdentity": {  
"name" : "A Shop Name",  
"addressNumber" : "A Number",  
"postCode" : "A Post Code",  
“type”: “A place type”  
},  
"PlaceGeoCode": {  
"shopLongitude": the shop's longitude,  
"shopLatitude": the shop's latitude  
}  
}  


##Some Potential Improvement Area
1. Add authentication
2. Add optimistic concurrent control over accesses
3. Increase test coverage and add integration test.
4. Use proper log

