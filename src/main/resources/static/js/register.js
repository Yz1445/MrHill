// $(document).ready(function(){
//     $("#Submit").click(submitFrom);
// });
// function submitFrom() {
//     $("#registerFormId").submit();
// }

layui.use('form', function(){
    var form = layui.form;
    form.render();   //表单渲染，得渲染才会有效果显示出来
    layer.msg("js重新渲染完成！")
})


