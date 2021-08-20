## Prerequisites
Have installed the maven and Java.

## Running the Example
Follow the steps below to run the example:

1. run in the root folder mvn spring-boot:run

2. Access http://localhost:8080/health - to check if the server is up and running.
        
3. Access http://localhost:8080/weather?city=lisbon - you don't have api key so you should receive 403 errror
    
4.  Access http://localhost:8080/weather?city=lisbon&apikey=my-valid-api-key - you should be able to see the results
    

## Future
 1 - Missing error Handling
 2 - missing show better when some error comes up
 3 - allow to get from other places not only Portugal
 4 - make it more scalable
 

