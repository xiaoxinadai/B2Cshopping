$(document).ready(function () {
    $('.div-span-title-bottom-distance').hover(
        function () {
            $(this).find("span").css("font-weight", "bold");
        },
        function () {
            $(this).find("span").css("font-weight", "normal");
        });
    layui.use('laypage', function(){
        var laypage = layui.laypage;

        //执行一个laypage实例
        laypage.render({
            elem: 'div__page' //注意，这里的 test1 是 ID，不用加 # 号
            ,count: pageTotal //数据总数，从服务端得到
            ,limit: pageSize, // 每页显示的条数
            limits: [1,2,5], // 每页条数的选择项
            curr:pageNum,
            layout: ['prev', 'page', 'next', 'limit', 'count', 'skip'],
            jump:function (obj,first){
                if (!first){
                    console.log("this.limit-------------------"+this.limit)
                    postDirect(
                        '/personal/order/form',
                        {
                            pageNum:obj.curr,
                            pageSize:this.limit
                        }
                    );
                }
            }
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
});