$(function (){
    $("#cart-button").on("click",function (){
        let amount = $("#product-amount").val();
        console.log(amount)
        $.post('/product/test?amount='+amount,
            function (testjson){
                console.log(testjson)
            })
    });
})