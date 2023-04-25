$(function (){

    // 实现点击页面任何位置，验证码报错信息消失
    $("body").mousedown(function (event){
        if(event.button == 0){
            $(".spanCaptchaError").hide();
        }
    });
    // 检验用户名的长度
    $('#username').blur(function (){
        let username = $(this).val();
        if (username.length>12 || username.length<3){
            $(this).val('');
            // layer.msg('用户名长度不小于3位不超出12位！',{time:1000});
            $('.divCaptchaError').append('<span class="spanCaptchaError">用户名长度不小于3位不超出12位！</span>');
        }
    });
    //对密码进行检验
    $('#password').blur(function (){
        let password = $(this).val();
        if (password.length>16 || password.length<6){
            $(this).val('');
            // layer.msg('用户名长度不小于6位不超出16位！',{time:1000});
            $('.divCaptchaError').append('<span class="spanCaptchaError">用户名长度不小于6位不超出16位！</span>');
        }
    });
    //对确认密码进行校验
    $('#passwordAgain').blur(function (){
        let passwordAgain = $(this).val();
        let password = $(this).parent().parent().find('#password').val();
        if (password != passwordAgain){
            $(this).val('');
            // layer.msg('两次密码不一致！',{time:1000});
            $('.divCaptchaError').append('<span class="spanCaptchaError">两次密码不一致！</span>');
        }
    });
    // 验证手机号码格式
    $('#phone').blur(function (){
        let phone = $(this).val();
        if (phone.length!=11){
            $(this).val('');
            // layer.msg('手机号码格式不正确！',{time:1000});
            $('.divCaptchaError').append('<span class="spanCaptchaError">手机号码格式不正确！</span>');
        }
    });
    // 验证是否满足表单提交的条件
    $('#registerForm').submit(function (event){
        event.preventDefault();
        let username = $('#username').val();
        let password = $('#password').val();
        let passwordAgain = $('#passwordAgain').val();
        let phone = $('#phone').val();
        let inputCaptcha = $('.inputcaptcha').val();
        if (!username || !password || !passwordAgain || !phone || !inputCaptcha){
            layer.msg('注册信息不全！',{time:1000});
            // $('.divCaptchaError').append('<span class="spanCaptchaError">注册信息不全！</span>');
        }else {
            this.submit();
        }
    });
})