
$(document).ready(function () {
    // $("#topTiles").load("topTiles.html");
    $("#zhuce").click(userControl);
});
function topTIles() {
    alert("aaaa")
    $.ajax({
        url:"/MrHill/topTiles",
        type:"GET",
        success:function (data) {
            console.info("111111111",data)
            // var result = $(data).find("#topTiles")
            $("#topTiles").html(data);
            console.info("111112221111")
        }
    });
}
function register() {
    $.ajax({
        url:"/MrHill/register",
        type:"GET",
        success:function (data) {
            console.info("111111111",data)
            $("#topTiles").html(data);
        }
    });
}
function userControl() {
    $.ajax({
        url:"/MrHill/getUserControl",
        type:"GET",
        success:function (data) {
            console.info("111111111",data)
            $("#topTiles").html(data);
        }
    });
}