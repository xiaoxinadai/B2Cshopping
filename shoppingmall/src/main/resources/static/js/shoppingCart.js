$(function (){

    $('.div-content-product-cart-size').each(function (){

        //点击更改数量按钮
        $(this).find('.input-product-number').change(function (){
            let productCartId = $(this).parent().siblings('.checkboxDiv').find('.productCartId').val();
            var productAmount = $(this).val();
            console.log('-----------------'+productAmount);
            var productPrice = $(this).parent().parent().find('.div-price-wid-size').find('span').text().substring(1);
            console.log('--------------------'+productPrice);
            var totalPrice = productPrice * productAmount;
            console.log('-----------------------'+totalPrice);
            $(this).parent().siblings('.div-total-wid-size').find('span').text('￥'+totalPrice.toFixed(2));
            $.ajax({
                url: "/shoppingCart/updateDataFromCart",
                type: "POST",
                data: {
                    // 在这里添加你要发送的数据
                    productCartId:productCartId,
                    productAmount:productAmount,
                    totalPrice:totalPrice
                },
                success: function(){
                    // 在这里处理响应数据
                    console.log('成功：更新数据库中的数据');
                },
                error: function(){
                    // 在这里处理错误
                    console.log('失败：更新数据库中的数据');
                }
            });
        });

        //点击删除按钮
        $(this).find('.div-caozuo-wid-size').find('img').click(function (e){
            let productCartId = $(this).parent().siblings('.checkboxDiv').find('.productCartId').val();
            $.ajax({
                url: "/shoppingCart/deleteByCartId",
                type: "POST",
                data: {
                    // 在这里添加你要发送的数据
                    productCartId:productCartId
                },
                success: function(){
                    // 在这里处理响应数据
                    location.reload();
                    console.log('成功：删除数据库中的数据');
                },
                error: function(){
                    // 在这里处理错误
                    console.log('失败：删除数据库中的数据');
                }
            });
        });
    });



    function postDirect(url, params) {
        var temp = document.createElement("form"); //创建form表单
        temp.action = url;
        temp.method = "post";
        temp.style.display = "none";//表单样式为隐藏
        for (var item in params) {//初始化表单内部的控件
            //根据实际情况创建不同的标签元素
            var opt =document.createElement("input");  //添加input标签
            opt.type="text";   //类型为text
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