$(document).ready(function() {
    $("#rakutenicon").click(function() {
        console.log("ok");
        var src = $(this).children('img').attr('src');
        $("#selectedicon").attr("src",src);
        console.log(src);
        
    });
});