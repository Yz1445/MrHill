
$(document).ready(function(){
    $("#login").click(submitFrom);
});
function submitFrom() {
    $("#fromId").submit();
}
function f() {
    var password = $("#password").val();
    var username = $("#username").val();
    console.info(password);
    alert(username);
    alert(password);
    $.ajax({
        url:"/MrHill/logindemo",
        data:{username:username,password:password},
        type:"POST",
        dataTpye:"json",
        success:function (data) {
            console.info(data)
        }
    });
}