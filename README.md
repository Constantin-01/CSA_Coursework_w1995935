Part 1.1 
JAX-RS creates a new resource class instance for every incoming request by default,
meaning that I must create a separate MockDatabase class where I store my data 
structures(maps/lists) inside static variables so that my data does not get lost
when a new instance is created. This works because each of the new resource class instance
will be able to access the separate MockDatabase class. Since multiple requests can happen at the same
time to avoid race conditions the data must be protected using thread safe data structures or syncronisation 
to prevent multiple requests updating the data incorrectly at the same time.

Part 1.2
Providing hyper media in the server response is considered a hallmark in 
RESTful design as it improves decoupling between the client and the server
as the client does not need to always depend on fixed endpoint structures.
This way the client can access the server even if the server changes its
URL's as it can easily follow the hyperlinks provided. 
The benefit the client because the server is easier to use compared to having
to rely on static documantaion meaning that it decreases  the time needed to find a 
specific endpoint.

Part 2.1
When the server returns room ID's only, the size of the response is smaller possitively
affecting the network as it uses less bandwith. The side affect is that the client has
to do more processing as it must use either specific filters and aslo more HTTP requests 
to retrieve a all information the room object contains.
Returning a full room object requires more bandwith as it contains more data so in a 
large server system where the room object contains heavy amount of data, the bandwith
can get overloaded so it can negatively affect the network efficiency wehn there are loads
of the entire data of the room objects. Whereas it positively affects the client side as it 
has to do less processing, it has to send less HTTP requests to get a larger amount of 
data.

Part 2.2
The delete operation in my implementation is idempotent as it does not leave the server in
a different state regardless of requesting the same DELERE request multiple times.
If a client mistakenly sends the same DELETE request for a specific room multiple times,
on the first request it will delete the room object with the specified ID from the list of 
room object, but requestion the same DELETE operation a second or third time will not make
any change to the server anymore as the specified room ID in the URI does not exist anymore 
after the first DELETE request. So on the first itteration the server will respond back with
a 204 No Content message meaning that the deletion succesfully happened and on the second or third
itteration the server will respond back with a 404 Not Found error message.

Part 3.1
As we explicitly use the @Consumes(MediaType.APPLICATION_JSON) annotiation on the POST method
this means that my server only accepts requests with the body's payload in Json format. Meaning
that if a client sends a request in xml or plain text format then the server will give back a  
415 Unsupported Media Type response, becasue the Content-Type in the request header does not 
mach the media type that the server consumes so the resource POST method will not be executed.

Part 3.2
Query Parameters are considered the better filtering approach as it adds flexibility to the system. 
It is easier to build on top to add more filtering parameters which help to narrow down the search for
the intended resource. This ensures simplicity as it does not require the server to contain many 
different URL paths for every possible filter. Also including the intended filter withing a path 
parameter will make the filter look as if it was part of the resource hierarchy when it is just a 
searching condition. In conclusion Query Parameter is generally considered the superior filtering 
approach for a collection of resource instances whereas teh Path Parameter is normally used to 
identify a specific resource.

Part 4.1
Delegating logic to separate classes provides easier maintenance and scalability. Also by splitting
the logic gives clear responsibility to each class it gives better readability as it avoids having a
huge class with many nested paths. By allowing separation of concerns it makes each class easier to 
read which also means that it will be easier to debug especially when creating a big API server. As 
the API grows there can be multiple sub-resources added so the structure stays clean allowing scalability.
Instead of having the logic tightly coupled, the logic becomes modular so the sub resource classes can
be easily reused and extended. Also, decoupled classes do not affect each other when the logic inside one 
of them is changed, so it is easier to maintain the system. When it comes to the actual exercise implemented
in part 4.1, the parent resource only routes to the specific sensorReadingResource instance which will after
handle the actual operations, in conclusion allowing separation of concerns: decoupling.

Part 5.2
 Question: Why is HTTP 422 often considered more semantically accurate than a standard
404 when the issue is a missing reference inside a valid JSON payload

The 404 Not Found response is generally used for something in the server not existing.
Whereas the HHTP 422 Unprocessable response is used for scenarios where the Json body
request is valid but there is an inconsistency with a certain data within the Json 
body. For example, referencing a roomId that does not exist. Therefore tieing back to
the question, the HTTP 422 response is more semantically accurate because the Jason
request is valid but it contains invalid data inside. 

Part 5.4
By exposing internal Java stack traces to external API consumers, specific information from
the stack trace can give an attacker a great advantage because due to the stack tracke
an attacker can duduce the internal structure of the server, see at what line the specific
error occured giving the attcker more information of server vilnerabilities. Addiional
infromation that can be found in the stack trace is package names, class names, variable 
names which all reveal where specific potential weaknesses are found int the server sytem.
Furthermore from the stack trace an attacker can deduce what the database structure is 
like, exposing weeknesses on the database and also it can even provide the libraries 
used which further expands on the leaked information of the server system.

Part 5.5
Using JAX-RS filters for cross cutting concers like logging is advantageous because 
it is implementd within a singular class which centralises the logging logic and it 
automatically applies it to multiple requests, instead of manually duplicationg the
Logger.info() statements inside every single resource method.


API Design Overview:

The API application that I have created contains 3 classes that represent the Rooms the sensors 
and the sensor readings. Instances of those objects are saved in a mock database class in lists
data structure. The resources of those classes contain functionallities as adding an instance to
the server, deleting an instance using its id in the URI, retrievig the list of object instances
stored in the system for all resources. The first thing incorporated in the system that the 
client can request is by using the base endpoint which will retrieve some Business metadata
and its resources with their specific hyper links. My application also allows searching for certain 
specific instances by using Query Parameters filtering, for example retrieving a certain type of sensor
object. 


Sample Curl Commands:

Discovery:
curl:  http://localhost:8080/CSA_Coursework_w1995935/api/v1

response:

{
    "version": "v1",
    "contact": "information@info.com",
    "resources": {
        "rooms": "/api/v1/rooms",
        "sensors": "/api/v1/sensors",
        "sensorReadings": "/api/v1/sensors/{sensorId}/sensorReadings"
    }
}

Get all rooms:
curl: http://localhost:8080/CSA_Coursework_w1995935/api/v1/rooms
[
    {
        "id": "R-1",
        "name": "Lecture Hall",
        "capacity": 200,
        "sensorIds": []
    },
    {
        "id": "R-2",
        "name": "Seminar Class",
        "capacity": 30,
        "sensorIds": []
   }
]

Create a room:
curl: http://localhost:8080/CSA_Coursework_w1995935/api/v1/rooms
POST
    {
        "id": "R-8",
        "name": "Lecture Hall 2",
        "capacity": 200,
        "sensorIds": []
    }

Get a specific room by id:
curl: http://localhost:8080/CSA_Coursework_w1995935/api/v1/rooms/R-1
{
    "id": "R-1",
    "name": "Lecture Hall",
    "capacity": 200,
    "sensorIds": []
}

Get all sensors:
curl: http://localhost:8080/CSA_Coursework_w1995935/api/v1/sensors
[
    {
        "id": "T-1",
        "type": "Temperature",
        "status": "Active",
        "currentValue": 19.0,
        "roomId": "R-1"
    },
    {
        "id": "T-2",
        "type": "CO2",
        "status": "Active",
        "currentValue": 17.5,
        "roomId": "R-2"
    }
]

Create a sensor:
curl: http://localhost:8080/CSA_Coursework_w1995935/api/v1/sensors
POST
{
    "id": "T-6",
    "type": "Temperature",
    "status": "Active",
    "currentValue": 19.0,
    "roomId": "R-2"
}

Add new sensor readings:
curl:http://localhost:8080/CSA_Coursework_w1995935/api/v1/sensors/T-1/readings
POST
{
  "timestamp": 174523794,
  "value": 77.2
}

Get specific sensor by filtering:
Curl:http://localhost:8080/CSA_Coursework_w1995935/api/v1/sensors?type=CO2
[
    {
        "id": "T-2",
        "type": "CO2",
        "status": "Active",
        "currentValue": 17.5,
        "roomId": "R-2"
    }
]





