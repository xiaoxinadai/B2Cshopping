$(function (){

    // 实现点击页面任何位置，验证码报错信息消失
    $("body").mousedown(function (event){
        if(event.button == 0){
            $("#captchaid").hide();
        }
    });
})