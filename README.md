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


