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
