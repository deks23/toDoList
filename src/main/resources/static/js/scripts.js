$(document).ready(function() {
    console.log("HELLO");
    $.ajax({
        url: "localhost:8080/rest/GetTasks"
    }).then(function(data) {
       console.log(data);
    });
});