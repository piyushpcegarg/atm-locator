# atm-locator

A spring boot project with basic spring security in place. which consumes a rest-api, and presents a neat UI with output of the rest api encapsulated in a Datatable using jquery.

How it was Implemented ?

Utilized spring initializer project to generate the basic code structure and architecture of the application.
- Added thymeleaf templating engine, with basic spring security starter.
- Resttemplate calls ( https://www.ing.nl/api/locator/atms/ ) for ING web service.
- The ING web-service provides a malformed json response, containing a few garbage characters in the beginning, this was adjusted in the api and proper json response is parsed to populate Data Transfer Object's.
- jQuery and Datatables.js is utilized along with Bootstrap.css for implementing UI elements.

How is the Architecture Designed ?

A basic spring MVC design : Request Callstack : Controller -> Service -> Repository and vice a versa for response. with a layer of application level security exposing all service / resource calls as authenticated.

Controllers :
AtmController.java : exposes 2 rest api's 
- /locations : Lists all the atm addresses exposed by ING atm locator service as a proper JSON respone.
- /locations/{city} : Filters and lists all locations based on provided city as a proper JSON response.

Services :
AtmLocator.java : implements business logic behind the exposed web services utilizing output from repository.

Repositories :
AtmDataPopulator.java : utilizes spring rest-template for consuming the ING ATM locator service.

User interface :

- /home OR / : Home page.
- /login     : Login page. uses in-memory auth : Credentials as:
-                 username : user , password : password
- /atm       : Page that lists all the atm addresses exposed by ING service in form of a Datatable which has all the functions available for sorting , Live search and pagination.

Tools used :
- Gradle
- JDK 11
- Spring boot
- Spring Security
- Themeleaf
- Embedded Tomcat

How to Run ?

- clone the repo.
- ./gradlew bootRun
- Open http://localhost:8080/

To create docker image and run using docker

- ./gradlew bootBuildImage
- docker run -d -p 8080:8080 --name atm-locator atm-locator:0.0.1-SNAPSHOT
