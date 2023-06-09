# ZSS Book Shop Application Assignment

The web service allows users to manage books in categories. The service exposes REST APIs to add, update, fetch and filter books and categories. The REST API accepts and provides JSON for all endpoints.

## Architecture

The web service was implemented based on the Model-View-Controller(MVC) architectural pattern. MVC separates an application into three main logical components: model, view and controller where each component is built to handle a specific aspect of an application depending on purpose. This segregation makes the application modifiable, scalable and easy to maintain.

## Frameworks and Modules Used

|**Feature**         |      **Framework/Module**|
|--------------------|--------------------------| 
|Bookshop       	 |      Springboot          |
|Logging             |      SLF4J               |
|Database            |      Postgres           |

## Prerequisites
- JDK 11+
- SpringBoot 2.5.2
- Maven 3+
- Postman

## Assumptions

The following assumptions were made during the development of the solution:
- Book titles where not validated with the assumption that books can have the same name.

## Steps to build and run web service

Book and Category tables will be created automatically when the application is run. The database only has two tables.

create a Postgres database called bookshop;
create user ‘postgres‘ with password ‘password’;
grant all privileges on database bookshop to postgres;

1. Open terminal
2. Navigate to the project folder
3. Run “mvn clean install”
4. Run “java -jar target/ bookshop-0.0.1-SNAPSHOT.jar”(By default the application runs on localhost:8080, but can be specified in the configurations)

After the application is run, the database tables populate with dummy data, adding multiple categories and books

## Checking health of the APIs

Spring Actuator is implemented to monitor health of the application and endpoints

### Sample endpoints to use 

Check overall health : http://localhost:8080/actuator/health

## Endpoint documentation

Documentation for the endpoints has been done using Swagger.

Swagger endpoint : http://localhost:8080/swagger-ui/index.html

## How to operate
1. Create a Category using the endpoint documented here : http://localhost:8080/swagger-ui/index.html#/category-controller/createCategoryUsingPOST
2. Add a Book using the categoryId returned above using the endpoint documented here : http://localhost:8080/swagger-ui/index.html#/book-controller/createBookUsingPOST
NB The rest of the CRUD operation functionalities can be found on the Swagger endpoint.

## Incomplete tasks
1. The ability to purchase a book by integrating with the Purchase API is not completed due to partial documentation and the unavailability of the API from my end : https://lab.v.co.zw/interview. 

## Future developments
1. Customise Controller Advice Error codes to enable better synchronization with the front-end.
2. Add book quantity property to book object.
3. Secure endpoints using OAuth 2.0 authentication.
4. Implementing Kafka stream or Active MQ queue to enable offline transactions to the external Purchase API.
5. Deploy the application in a Docker container and also have a persistent database setup in a Docker container (Postgres).
6. Increase Test coverage