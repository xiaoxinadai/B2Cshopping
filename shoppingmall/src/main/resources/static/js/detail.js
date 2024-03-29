$(function () {

    // 点击加入购物车按钮跳转到我的购物车页面
    $("#cart-button").on("click", function () {
        let amount = Number($("#product-amount").val());
        let productSpec = $('.div-chose-guige-color').children(1).text();
        if (productSpec ===''){
            layer.msg('请选择规格',{time:1000});
            return;
        }
        console.log("productSpec---------------"+productSpec);
        let totalPrice = productPrice * amount
        console.log(amount);
        console.log(productSpec);
        if (sessionUser === null){
            // 将信息存储到sessionStorage中
            let shoppingCartList = {productId:productId,productSpec:productSpec,productAmount:amount,productTotalPrice:totalPrice.toFixed(2)};
            console.log(sessionStorage.getItem("shoppingCartListArray"));
            if (sessionStorage.getItem("shoppingCartListArray") === null){
                let shoppingCartListArray = [];
                shoppingCartListArray.push(shoppingCartList);
                sessionStorage.setItem('shoppingCartListArray',JSON.stringify(shoppingCartListArray));
            }else {
                let item = sessionStorage.getItem("shoppingCartListArray");
                let parse = JSON.parse(item);
                parse.unshift(shoppingCartList);
                sessionStorage.setItem('shoppingCartListArray',JSON.stringify(parse));
            }
        }
        postDirect("/product/addToShoppingCart",{
            productId:productId,
            productSpec:productSpec,
            amount:amount,
            totalPrice:totalPrice,
            shoppingCartListArray:JSON.parse(JSON.stringify(sessionStorage.getItem("shoppingCartListArray")))
        });
    });
    // $("#cart-button").on("click", function () {
    //     let amount = $("#product-amount").val();
    //     console.log(amount)
    //     $.post('/product/shoppingCart?amount=' + amount,
    //         function () {
    //             location.href = "/product"
    //         })
    // });

    let targetColorDiv = $('.div-guige');
    // 监听触发div的点击事件
    $('.div-guige').click(function() {
        // 检查目标div是否有class
        if ($(this).hasClass('div-chose-guige-color')) {
            // 如果有，删除class
            targetColorDiv.removeClass('div-chose-guige-color');
        } else {
            // 如果没有，添加class
            targetColorDiv.removeClass('div-chose-guige-color');
            $(this).addClass('div-chose-guige-color');
        }
    });

    function postDirect(url, params) {
        var temp = document.createElement("form"); //创建form表单
        temp.action = url;
        temp.method = "post";
        temp.style.display = "none";//表单样式为隐藏
        for (var item in params) {//初始化表单内部的控件
            //根据实际情况创建不同的标签元素
            var opt =document.createElement("input");  //添加input标签
            opt.type= "text";      //类型为text
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