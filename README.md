# RainyHills Application
This application intends to calculate the water volume between hills after a rain. It receives an array of integer as input, representing the hills height values.

## Architecture and technologies used

The architecture and technologies used were intended to be as simple and light as possible.
The picture below is a simple draft of the architecture used:

https://github.com/lbianchini/RainyHills/blob/master/RainyHills/RainyHills%20-%20Diagram.png?raw=true

The tests and frontend were intentionaly build in two separated applications for the following reasons: 

* Tests: Run tests outside the main project as a integrated test consuming the REST Services and asserting its results as a external consumer.

* Frontend: Show the decoupling of project, frontend independent, shows the possibility do either front and backend be deployed in separated servers, the possibility to be scaled independently, and shows the backend services as a single entry point for business logic.

The framework used in this application were: 
- JavaEE 7
- JDK 1.8
- Dozer 5.5.1
- Jackson Databind 2.9.0
- RESTAssured 3.0.3
- Glassfish 4.1 Embedded by Cargo Plugin
- Angular 2 for Frontend

## Water Volume calculation algorithm
The algorithm implemented to calculate the water volume has a linear order of growth performing a single iteration on the array of integer used as input. It iterates on array of integer from both sides(left and right), starting from left, to determine the highest hill from each side.

A stress test was performed using JMeter just to exemplify the performance of consuming the REST Service responsible for this calculation:
<IMAGE>

100000 requests were performed with an average of 5ms per request and a throughput of 1852 requests per second.

## Build, tests and run
The easiest way to build, test and run the application is import this projects into a IDE such as Ecclipse with a Application Server such as Wildfly configured and publish and run RainyHills-Frontend-Angular and RainyHills-Services in it.

Another way is, with Maven installed and cofigured, go to RainyHills-Services directory using command prompt and type the following command: 
```
mvn clean package cargo:run
```

After this, go to RainyHills-Services-Tests directory and type:
```
mvn test
```

To run the application, make sure that RainyHills-Services still running, go to RainyHills-Frontend-Angular and type:
```
mvn clean package cargo:run
```

After this go to URL Address:
```
http://localhost:8081/RainyHills-Angular
```
