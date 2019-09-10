$(function(){
    // Set value for creating dynamic url
    $(".lunch_slct").click(function(){
        if(this.checked){
            $(".lunch_slct").val("1");
        } else {
            $(".lunch_slct").val("0");
        };
    });
    $(".card_slct").click(function(){
        if(this.checked){
            $(".card_slct").val("1");
        } else {
            $(".card_slct").val("0");
        };
    });
    $(".nosmoking_slct").click(function(){
        if(this.checked){
            $(".nosmoking_slct").val("1");
        } else {
            $(".nosmoking_slct").val("0");
        };
    });
    // /Set value for creating dynamic url

    // Create dynamic query with realtime
    $.ajax
});