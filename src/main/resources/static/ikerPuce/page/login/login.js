layui.config({
	base:'../../js/common/'
})

layui.use(['form','layer','jquery','common'],function(){
	
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
		common = layui.common,
        $ = layui.jquery;

    $(".loginBody .seraph").click(function(){
        layer.msg("这只是做个样式，至于功能，你见过哪个后台能这样登录的？还是老老实实的找管理员去注册吧",{
            time:5000
        });
    })
	var vm = new Vue({
		el:"#mount",
		data: common
	});	
    //登录按钮
    form.on("submit(login)",function(data){
		var url = "../../json/login.json";
		var type = "GET";
		if (common.pattern == 1) {
			url="http://127.0.0.1:9000/MrHill/logindemo",
			type="POST";
		}
		$.ajax({
			url:url,
			data:{username:data.field.userName,password:data.field.password},
			type:type,
			dataTpye:"json",
			success:function (result) {
				console.info(result)
				var obj;
				 //判断是否是json格式
				 if((typeof result=='object')&&result.constructor==Object){
					 obj=result;
				 }else{
					 obj  = eval("("+result+")");
				 }
				if (null != obj && "null" != obj) {
					window.sessionStorage.setItem("userName",obj.name);//写入
					window.location.href = "/ikerPuce/index.html";
				} else {
					layer.msg("登录失败!账户或密码错误！")
					// common.UserNameLabel = "账号错误！"
				}
			}
		});
        return false;
    })

    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    })
})
