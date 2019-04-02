$(document).ready(function() {

    $.ajax({
        type: "GET",
        url: 'http://localhost:8080/standard/getall',
        headers: {
            "Content-Type": "application/json"
        },
        success: function (data) {
            $( "#selectstandard" ).prop( "disabled", true );
            $( "#selectstid" ).prop( "disabled", true );
            var i=0;
            for(i=0;i<data.length;i++) {
                $('#standard').append("<option value=\"" + data[i].standard + "\">" + data[i].standard + "</option>");
            }
        }
    });

    $("select.standard").change(function(){
        var selectedstandard = $(this).children("option:selected").val();
        $.ajax({
            type: "GET",
            url: 'http://localhost:8080/student/standard/'+selectedstandard,
            headers: {
                "Content-Type": "application/json"
            },
            success: function (data) {
                $('#stid').empty();
                $('#stid').append("<option value=\"select\" id=\"selectstid\">select</option>");
                $( "#selectstid" ).prop( "disabled", true );
                var i=0;
                for(i=0;i<data.length;i++) {
                    $('#stid').append("<option value=\"" + data[i].id + "\">" + data[i].id + "</option>");
                }
            }
        });
    });


    function fillInfo(result){
        $("#infodiv").append("<h3>Name : "+result[0].name+"</h3>");
        $("#infodiv").append("<h3>Standard : "+result[0].standard+"</h3>");
    }
    function fillCourses(courses) {
        $("#c1").append(courses[0].name);
        $("#c2").append(courses[1].name);
        $("#c3").append(courses[2].name)
    }
    function fillCourseReport(courseinfo,tutorid,i) {
        // var studentid=$('#studentid').val();
        var studentid = $('#stid').find(":selected").text();
        $('#cc'+i).text(courseinfo[i].id);
        $('#ct'+i).text(courseinfo[i].name);
        $.ajax({
            type: "GET",
            url: 'http://localhost:8080/teacher/getname/' + tutorid,
            headers: {
                "Content-Type": "application/json"
            },
            success: function (data) {
                $('#tn'+i).text(data['name']);
            }
        });
        $.ajax({
            type: "GET",
            url: 'http://localhost:8080/attendance/get/' + studentid,
            headers: {
                "Content-Type": "application/json"
            },
            success: function (data) {
                $('#at'+i).text(data['course'.concat(i+1)]);
                if(i==0){
                    $("#summarydiv").append("<h3>Average Attendance  :  "+ data['average'] +"</h3>");
                    if(data['eligibility']=="Eligible"){
                        $("#summarydiv").append("<span  style=\"font-size: 14pt\">Eligibility  :  </span><span style=\"color: green; font-size: 14pt\">"+ data['eligibility'] +"</span>");
                    }
                    else{
                        $("#summarydiv").append("<span  style=\"font-size: 14pt\">Eligibility  :  </span><span style=\"color: red;letter-spacing: 2px;\">"+ data['eligibility'] +"</span>");
                    }
                }
            }
        });
        // alert($('#reporttable').innerHTML.toString());
        $('#report').removeClass('hidden');
        $('#reporttable').removeClass('hidden');
    }
    function generateReport(courses) {
        var i;
        // for (i=0;i<3;i++) {
            $.ajax({
                type: "GET",
                url: 'http://localhost:8080/teacher/get/' + courses[0].id,
                headers: {
                    "Content-Type": "application/json"
                },
                success: function (data) {
                    // alert(data['teacherid'] +"      "+ i);
                    fillCourseReport(courses, data['teacherid'], 0);

                }
            });
        $.ajax({
            type: "GET",
            url: 'http://localhost:8080/teacher/get/' + courses[1].id,
            headers: {
                "Content-Type": "application/json"
            },
            success: function (data) {
                // alert(data['teacherid'] +"      "+ i);
                fillCourseReport(courses, data['teacherid'], 1);

            }
        });
        $.ajax({
            type: "GET",
            url: 'http://localhost:8080/teacher/get/' + courses[2].id,
            headers: {
                "Content-Type": "application/json"
            },
            success: function (data) {
                // alert(data['teacherid'] +"      "+ i);
                fillCourseReport(courses, data['teacherid'], 2);

            }
        });
        // }
    }

    function findCourses(standard,f){
        $.ajax({
            type : "GET",
            url : 'http://localhost:8080/courses/get/'+standard,
            headers : {
                "Content-Type" : "application/json"
            },
            success : function(data) {
                if(data.toString()==''){
                    alert("No Courses Added for this standard")
                }
                else {
                    if(f){
                        fillCourses(data);
                    }
                    else{
                        generateReport(data);
                    }
                }
            },
            error : function(data) {
                alert('no results');
            }
        });
    }

    $("#search").click(function() {
        // var id=$('#studentid').val();
        var id = $('#stid').find(":selected").text();
        $.ajax({
            type : "GET",
            url : 'http://localhost:8080/get/'+id,
            headers : {
                "Content-Type" : "application/json"
            },
            success : function(data) {
                var jsonArray = [];
                if(data.toString()==''){
                    alert("No results")
                }
                else {
                    jsonArray.push(data);
                    fillInfo(jsonArray);
                    $("#generate").attr("disabled", true);
                    $("#search").attr("disabled", true);
                    findCourses(data['standard'],true);
                    $("#add").removeClass("hidden");
                }
            },
            error : function(data) {
                alert('no results');
            }
        });
    });

    $("#submit").click(function() {
        var id= $('#stid').find(":selected").text();
        var c1=$('#course1').val();
        var c2=$('#course2').val();
        var c3=$('#course3').val();
        var attendance = {
            'studentid': id,
            'course1': c1,
            'course2': c2,
            'course3': c3
        };
        var aJson = JSON.stringify(attendance);
        $.ajax({
            type : "POST",
            url : 'http://localhost:8080/attendance/save',
            headers : {
                "Content-Type" : "application/json"
            },
            data:aJson,
            success : function(data) {
                alert("Successfully Saved")
                location.reload();
            },
            error : function(data) {
                alert('Failed to save');
            }
        });
    });

    $('#generate').click(function () {
        // var id=$('#studentid').val();
        var id = $('#stid').find(":selected").text();
        $.ajax({
            type : "GET",
            url : 'http://localhost:8080/get/'+id,
            headers : {
                "Content-Type" : "application/json"
            },
            success : function(data) {
                var jsonArray = [];
                if(data.toString()==''){
                    alert("No results")
                }
                else {
                    jsonArray.push(data);
                    fillInfo(jsonArray);
                    $("#studentid").prop("readonly", true);
                    $("#generate").attr("disabled", true);
                    $("#search").attr("disabled", true);
                    findCourses(data['standard'],false);
                }
            },
            error : function(data) {
                alert('no results');
            }
        });
    });

});