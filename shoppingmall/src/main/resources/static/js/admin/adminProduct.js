$(document).ready(function (){
    $('.div-delete').click(function (){
        let productId = $(this).parent().siblings('.productId').val();
        console.log("productId-------------------------"+productId);
        postDirect(
            '/adminProduct/deleteProduct',
            {
                "productId":productId
            }
        );
    });

    let popupsOpen;

    $('.div-border-size').click(function (){
        //页面层
        popupsOpen = layer.open({
            type: 2,
            skin: 'layui-layer-rim', //加上边框
            area: ['420px', '380px'], //宽高
            content: 'http://localhost:8080/adminProduct/addProductView'
        });
    });



    $('.div-edit').click(function (){
        let productId = $(this).parent().siblings('.productId').val();
        console.log("productId-------------------------"+productId);

        layer.open({
            type: 2,
            skin: 'layui-layer-rim', //加上边框
            area: ['420px', '380px'], //宽高
            content: 'http://localhost:8080/adminProduct/addProductView?productId='+productId
        });
    });

    layui.use('flow', function(){
        var flow = layui.flow;
        //按屏加载图片
        flow.lazyimg({
            elem: '#LAY_demo3 img'
            ,scrollElem: '#LAY_demo3' //一般不用设置，此处只是演示需要。
        });
    });

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
})