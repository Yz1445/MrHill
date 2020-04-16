layui.define(["layer","jquery"],function(exports){
	var obj  = {
		common : function () {
			layer.msg("呵呵!!")
		},
		// name : "yangzhao",
		//单机模式OR非单机模式 0单机模式，1非单机模式
		pattern : 1,
		/**********************登录首页*****************************/
		UserNameLabel : "用户名",
		PasswordLabel : "密码",
		LonginButton : "登录",
		UserNameplaceholder : "请输入用户名",
		Passwordplaceholder : "请输入密码",
		LogoPath : "../../images/ikerpuce_logo.jpg",
		/***********************左侧导航栏**************************/
		Hello : "你好！",
		LogOnUser : "测试用户",
		LogOnUserAfter : ", 欢迎登录",
		LogoName : "亿科浦思科技",
		Search : "搜索页面或功能",
		BackstageHomePage : "后台首页1ss"
	}
	exports("common",obj);
});

