
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

