
$(document).ready(function() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/studentdetails',
        success: function(data){
            if(data){
                populateTable('allresults',data);
            }
            $("#infodiv").append("(This table is populated using the json file returned by the url 'http://localhost:8080/studentdetails')")
        },
        error: function(jqXHR, textStatus, errorThrown){
            alert('error: ' + textStatus + ': ' + errorThrown);
        }
    });



    $("#search").click(function() {

        var studentID=$('#id').val();
        $.ajax({
            type : "GET",
            url : 'http://localhost:8080/studentdetails/'+studentID,
            headers : {
                "Content-Type" : "application/json"
            },
            success : function(data) {
                var jsonArray = [];
                jsonArray.push(data);
                console.log(data);
                populateTable('searchresults',jsonArray);

            },
            error : function(data) {
                alert('no results');
            }
        });
    });

    $("#delete").click(function() {
        var studentID=$('#id').val();
        $.ajax({
            type : "DELETE",
            url : "http://localhost:8080/deletestudent/"+studentID,
            headers : {
                "Content-Type" : "application/json"
            },
            success : function(data) {
                alert("Successfully Deleted, press ok to view all student details..");
                location.reload();
            },
            error : function(data) {
            }
        });
    });

    $("#update").click(function() {
        $("#update").hide();
        $("#updatediv").removeClass("hidden");
    });

    $("#filter").click(function () {
        var starting=parseInt($('#starting').val());
        var ending=parseInt($('#ending').val());
        var temp=0;
        if(starting>ending){
            temp=starting; starting=ending; ending=temp;
        }
        $.ajax({
            type : "GET",
            url : "http://localhost:8080/filterstudentsbystandard/"+starting+"/"+ending,
            headers : {
                "Content-Type" : "application/json"
            },
            success : function(data) {
                populateTable('searchresults',data);
            },
            error : function(data) {
            }
        });

    })

    $("#update1").click(function() {

        var err=false;
        $('input[type="text"]').each(function() {
            if ($.trim($(this).val()) == '') {
                err=true;
            }
        });
        if(!err) {
            var studentID = $('#id').val();
            var studentStandard = $('#standard').val();
            var studentName = $('#name').val();

            var studentDetails = {
                'name': studentName,
                'standard': studentStandard,
                'id': studentID
            };
            var sdJson = JSON.stringify(studentDetails);
            alert(sdJson);
            $.ajax({
                type: "PUT",
                url: "http://localhost:8080/updatestudent/" + studentID,
                headers: {
                    "Content-Type": "application/json"
                },
                data: sdJson,
                success: function (data) {
                    alert("Successfully updated, press ok to view all student details..");
                    window.location.replace("http://localhost:8080/studentdetailstable");
                },
                error: function (data) {
                }
            });
        }
        else {
            alert("Please fill all fields");
        }
    });
});

function populateTable(table,data) {
    if(data){
        var len = data.length;
        var txt = "";
        if(len > 0){
            for(var i=0;i!=len;i++){
                if(data[i].id){
                    txt += "<tr><td>"+data[i].id+"</td><td>"+data[i].name+"</td><td>"+data[i].standard+"</td></tr>";
                }
            }
            if(txt != ""){
                $('#'+table).find("tr:gt(0)").remove();
                $('#'+table).append(txt).removeClass("hidden");
            }
        }
    }
}

