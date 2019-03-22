
$(document).ready(function() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/getall',
        success: function(data){
            if(data){
                populateTable('allresults',data);
            }
            $("#infodiv").append("(This table is populated using the json file returned by the url 'http://localhost:8080/getall')")
        },
        error: function(jqXHR, textStatus, errorThrown){
            alert('error: ' + textStatus + ': ' + errorThrown);
        }
    });

    $("#search").click(function() {
        var id=$('#id').val();
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
        var id=$('#id').val();
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
                    $.ajax({
                        type : "DELETE",
                        url : "http://localhost:8080/delete/"+id,
                        headers : {
                            "Content-Type" : "application/json"
                        },
                        success : function(data) {
                            alert("Successfully Deleted, press ok to view all db details..");
                            location.reload();
                        },
                        error : function(data) {
                        }
                    });
                }
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
        var start=parseInt($('#starting').val());
        var end=parseInt($('#ending').val());
        var temp=0;
        if(start>end){
            temp=start; start=end; end=temp;
        }
        $.ajax({
            type : "GET",
            url : "http://localhost:8080/get/student/"+start+"/"+end,
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
            var id= $('#id').val();
            var standard = $('#standard').val();
            var name = $('#name').val();

            var student = {
                'name': name,
                'standard': standard,
                'id': id
            };
            var sdJson = JSON.stringify(student);
            alert(sdJson);
            $.ajax({
                type: "PUT",
                url: "http://localhost:8080/update/" + id,
                headers: {
                    "Content-Type": "application/json"
                },
                data: sdJson,
                success: function (data) {
                    alert("Successfully updated, press ok to view all db details..");
                    window.location.replace("http://localhost:8080/home");
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

