
$(document).ready(function () {
    $.ajax({
        url:"/MrHill/register",
        type:"GET",
        success:function (data) {
            console.info("111111111",data)
            $("#index").html(data);
        }
    });
});
