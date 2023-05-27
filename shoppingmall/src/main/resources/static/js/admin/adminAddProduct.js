$(document).ready(function (){
    let imgSrc; //图片路径
    if (imgClose == 1){
        imgSrc = productImgLocation;
    };
    layui.use('upload', function () {
        var upload = layui.upload;

        //执行实例
        var uploadInst = upload.render({
            elem: '#test1', //绑定元素
            url: '/sys/uploadPicture', //上传接口
            accept: 'images',
            done: function (res) {
                //上传完毕回调
                // $('#demo1').attr('src', res.data.src);
                $('#demoText').html('上传成功');
                imgSrc = res.data.src;
            },
            error: function () {
                //请求异常回调
                $('#demoText').html('上传失败');
            }
        });
    });

    $('.div-submit-add-product').click(function (){
        let productName = $('.productName').val();
        let productPrice = $('.productPrice').val();
        let productDescription = $('.productDescription').val();
        let productCategory = $('.productCategory').val();
        $.ajax({
            url: '/adminProduct/addProductHandle',
            type: "POST",
            data: {
                // 在这里添加你要发送的数据
                id: productId,
                productName: productName,
                productPrice:Number(productPrice),
                productDescription: productDescription,
                productCategory: productCategory,
                imgLocation:imgSrc
            },
            success: function (res) {
                // 在这里处理响应数据
                if (res.data.closePopups === 1){
                    console.log("res.data.closePopups--------"+res.data.closePopups);
                    setTimeout(()=>{
                        parent.location.href="http://localhost:8080/admin/adminProduct";
                    },500);
                }
                // setTimeout(()=>{
                //     parent.location.href="http://localhost:8080/admin/adminProduct";
                // },1000);
                console.log('成功');
            },
            error: function () {
                // 在这里处理错误
                console.log('失败');
            }
        });
    });

    if (imgClose == 1){
        $('.productCategory').val(productCategory);
        console.log("------------------------------2");
        //添加赋值
        $('.productName').val(productName);
        $('.productPrice').val(productPrice);
        $('.productDescription').val(productDescription)
    };

    function postDirect(url, params) {
        var temp = document.createElement("form"); //创建form表单
        temp.action = url;
        temp.method = "post";
        temp.style.display = "none";//表单样式为隐藏
        for (var item in params) {//初始化表单内部的控件
            //根据实际情况创建不同的标签元素
            var opt = document.createElement("input");  //添加input标签
            opt.type = "text";   //类型为text
            opt.id = item;      //设置id属性
            opt.name = item;    //设置name属性
            opt.value = params[item];   //设置value属性
            temp.appendChild(opt);
        }
        document.body.appendChild(temp);
        temp.submit();
        return temp;
    }
});