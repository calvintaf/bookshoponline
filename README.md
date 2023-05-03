# ZSS Book Shop Application Assignment

The web service allows users to manage books in categories. The service exposes REST APIs to add, update, fetch, load and filter books and categories. The REST API accepts and provides JSON for all endpoints.

## Architecture

The web service was implemented based on the Model-View-Controller(MVC) architectural pattern. MVC separates an application into three main logical components: model, view and controller were each component is built to handle a specific aspect of an application depending on purpose. This segregation makes the application modifiable, scalable and easy to maintain.

## Frameworks and Modules Used

|**Feature**         |      **Framework/Module**|
|--------------------|--------------------------| 
|Bookshop       	 |      Springboot          |
|Logging             |      SLF4J               |
|Database            |      Postgres           |

## Prerequisites
- JDK 11
- SpringBoot 2.5.2
- Maven 3
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

## Future developments
1. Secure endpoints using OAuth 2.0 authentication.
2. Implementing Kafka stream or Active MQ queue to enable offline transactions to the external Purchase API.
3. Deploy the application in a docker container and also have a persistent database setup in a docker container (postgres).
4. Increase Test Coverage