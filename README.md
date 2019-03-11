# students-api


All in one UI - http://localhost:8080/studentdetailstable

(implemented using jquery,AJAX to make requests to server)


type - urls - data - use

GET - localhost:8080/studentdetails/ - noData - to retreive all rows

GET - localhost:8080/studentdetails/st003 - noData - to retrieve particular row

POST - localhost:8080/addstudent?content-type=application/json - adding new row

{
    "studentName": "name6",
    "studentStandard": 5,
    "studentID": "st005"
}

PUT - localhost:8080/updatestudent/st002 - updating a row

{ "studentName": "name8", "studentStandard": 8, "studentID": "st002" }

DELETE - localhost:8080/deletestudentdetails/st001 - noData - deleteing a row
