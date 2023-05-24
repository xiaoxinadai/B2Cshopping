$(function (){

    // 实现点击页面任何位置，验证码报错信息消失
    $("body").mousedown(function (event){
        if(event.button == 0){
            $("#captchaid").hide();
        }
    });
    //注册成功之后跳转到登录页面，给一个登录成功的提示
    if (registerSign === "1"){
        layer.msg('注册成功！',{time:1000});
    };

    //点击验证码实现刷新
    $('.imgdistance').click(function (){
        console.log("test----captcha")
        $.get("/user/captcha",function (data){
            $(this).html(data);
        });
    });
})