layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;

    $(".loginBody .seraph").click(function(){
        layer.msg("这只是做个样式，至于功能，你见过哪个后台能这样登录的？还是老老实实的找管理员去注册吧",{
            time:5000
        });
    })

    //登录按钮
    form.on("submit(login)",function(data){
		// console.info(data.field.userName)
		// layer.msg(data.field)
        // $(this).text("登录中...").attr("disabled","disabled").addClass("layui-disabled");
		// table.render({
		// 	// elem: this,
		// 	url:'http://127.0.0.1:9000/MrHill/test',
		// 	parseData: function(res){ 
		// 		console.info(res);
		// 	}
		// });
		$.ajax({
			 url:"http://127.0.0.1:9000/MrHill/logindemo",
			        data:{username:data.field.userName,password:data.field.password},
			        type:"POST",
			        dataTpye:"json",
			        success:function (result) {
						console.info(result)
						if (null != result && "null" != result) {
							var obj;
							 //判断是否是json格式
							 if((typeof result=='object')&&result.constructor==Object){
								 obj=result;
							 }else{
								 obj  = eval("("+result+")");
							 }
							 console.info("登录成功："+obj)
							 window.location.href = "/ikerPuce/index.html";
						}
						
			
			        }
		});
        setTimeout(function(){
            // window.location.href = "/ikerPuce/index.html";
        },1000);
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
