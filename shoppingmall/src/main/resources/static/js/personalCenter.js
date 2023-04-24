$(document).ready(function (){
    $('.div-span-title-bottom-distance').hover(
        function (){
            $(this).find("span").css("font-weight", "bold");
        },
        function() {
            $(this).find("span").css("font-weight", "normal");
        });
});