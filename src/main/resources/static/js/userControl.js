layer.msg('Hello World');
layui.use('table', function(){
    var table = layui.table;
    table.render({
        elem: '#UserTable'
        ,url:'/MrHill/getAllUser'
        // ,where:{}
        ,request:{
            pageName:"curr"
            ,limitName: 'nums'
        }
        ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        ,page: true //开启分页
        ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        ,totalRow: true //开启合计行
        ,cols: [[ //表头
            {type: 'checkbox', fixed: 'left'}
            ,{field: 'userId', title: 'ID', width:80, sort: true, fixed: 'left', totalRowText: '合计：'}
            ,{field: 'userName', title: '用户名', width:180}
            ,{field: 'role', title: '用户角色', width:180}
            ,{field: 'phone', title: '电话号码', width:180}
            // ,{field: 'level', title: '级别', width:80}
            ,{field: 'status', title: '状态', width:80}
            ,{fixed: 'right', width: 180, align:'center', toolbar: '#barDemo'}
        ]]
    });
    //监听头工具栏事件
    table.on('toolbar(test)', function(obj){//test ===lay-filter="test"
        var checkStatus = table.checkStatus(obj.config.id)
            ,data = checkStatus.data; //获取选中的数据
        switch(obj.event){
            case 'add':
                layer.msg('添加');
                $.post('/MrHill/register', {}, function(str){
                    layer.open({
                        type: 1
                        ,title:'添加用户'
                        ,area:['420px','500px']
                        ,scrollbar: false
                        ,content: str //注意，如果str是object，那么需要字符拼接。
                    });
                });
                break;
            case 'update':
                if(data.length === 0){
                    layer.msg('请选择一行');
                } else if(data.length > 1){
                    layer.msg('只能同时编辑一个');
                } else {
                    layer.alert('编辑 [id]：'+ checkStatus.data[0].id);

                    $.post('/MrHill/register', {userId:checkStatus.data[0].id}, function(str){
                        layer.open({
                            type: 1
                            ,title:'编辑用户'
                            ,area:['420px','500px']
                            ,scrollbar: false
                            ,content: str //注意，如果str是object，那么需要字符拼接。
                        });
                    });
                }
                break;
            case 'delete':
                if(data.length === 0){
                    layer.msg('请选择一行');
                } else {
                    layer.msg('删除');
                }
                break;
        };
    });
    //监听行工具事件
    table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            ,layEvent = obj.event; //获得 lay-event 对应的值
        if(layEvent === 'detail'){
            layer.msg('查看操作');
        } else if(layEvent === 'del'){
            layer.confirm('真的删除行么', function(index){
                obj.del(); //删除对应行（tr）的DOM结构
                layer.close(index);
                //向服务端发送删除指令
            });
        } else if(layEvent === 'edit'){
            console.info("-->>>",data.username)
            layer.msg(data.username);
        }
    });
});
