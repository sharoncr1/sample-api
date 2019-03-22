# students-api


All in one UI - http://localhost:8080/home

(implemented using jquery,AJAX to make requests to server)

note : 
1. There might be issues/errors regarding the mysql user password. Resolve the errors by editing the password in following        files : application.properties, application-integrationtest.properties and MainClass.java
2. There will be no data and so no output in the initial run. First insert some rows first through either UI or direct POST        request.

type - urls - data - use

GET - localhost:8080/getall/ - noData - to retreive all rows

GET - localhost:8080/get/st003 - noData - to retrieve particular row

POST - localhost:8080/add?content-type=application/json - adding new row

{
    "name": "name6",
    "standard": 5,
    "id": "st005"
}

PUT - localhost:8080/update/st002 - updating a row

{ "name": "name8", "standard": 8, "id": "st002" }

DELETE - localhost:8080/delete/st001 - noData - deleteing a row
