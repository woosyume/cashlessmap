var language;
$(function(){
    // Set language for creating dynamic url
    $('input[name="lang"]').change(function() {
        language = $(this).val();
        console.log(createJson(collectValuesAsList(language)));
    })
                    
    // Set options for creating dynamic url
    $(".rakutenpay").click(function(){
        if(this.checked){
            $(".rakutenpay").val("1");
            console.log(createJson(collectValuesAsList(language)));
        } else {
            $(".rakutenpay").val("0");
            console.log(createJson(collectValuesAsList(language)));
        };
    });
    $(".paypay").click(function(){
        if(this.checked){
            $(".paypay").val("1");
            console.log(createJson(collectValuesAsList(language)));
        } else {
            $(".paypay").val("0");
            console.log(createJson(collectValuesAsList(language)));
        };
    });
    $(".applepay").click(function(){
        if(this.checked){
            $(".applepay").val("1");
            console.log(createJson(collectValuesAsList(language)));
        } else {
            $(".applepay").val("0");
            console.log(createJson(collectValuesAsList(language)));
        };
    });
    // Set options for creating dynamic url
    $(".lunch_slct").click(function(){
        if(this.checked){
            $(".lunch_slct").val("1");
            console.log(createJson(collectValuesAsList(language)));
        } else {
            $(".lunch_slct").val("0");
            console.log(createJson(collectValuesAsList(language)));
        };
    });
    $(".card_slct").click(function(){
        if(this.checked){
            $(".card_slct").val("1");
            console.log(createJson(collectValuesAsList(language)));
        } else {
            $(".card_slct").val("0");
            console.log(createJson(collectValuesAsList(language)));
        };
    });
    $(".nosmoking_slct").click(function(){
        if(this.checked){
            $(".nosmoking_slct").val("1");
            requestToApi();
        } else {
            $(".nosmoking_slct").val("0");
            requestToApi();
        };
    });
});
$(function createJson(params) {
    var obj = new Object();
    obj.lang = params[0];
    obj.pay = params[1];
    obj.lunch = params[2];
    obj.nosmoking = params[3];
    obj.card = params[4];
    obj.latitude = params[5];
    obj.longitude = params[6];
    return obj;
})
function collectValuesAsList(lang) {
    var array  = new Array();
    // Language
    array.push(lang);
    
    // Pay
    var array_pay  = new Array();
    if ($('.rakutenpay').val() == 0 &&
     $('.paypay').val() == 0 &&
     $('.applepay').val() == 0) {
        array_pay.push("rakutenpay"); // Set rakuten pay as default
    } else {
        if ($('.rakutenpay').val() == 1) array_pay.push("rakutenpay");
        if ($('.paypay').val() == 1) array_pay.push("paypay");
        if ($('.applepay').val() == 1) array_pay.push("applepay");
    }
    array.push(array_pay);
    // Options
    array.push($('.lunch_slct').val());
    array.push($('.card_slct').val());
    array.push($('.nosmoking_slct').val());
    // TODO : latitude
    array.push("44.968046");
    array.push("-94.420307");
    return array;
}
function createJson(params) {
    var obj = new Object();
    obj.lang = params[0];
    obj.pay = params[1];
    obj.lunch = params[2];
    obj.nosmoking = params[3];
    obj.card = params[4];
    obj.latitude = params[5];
    obj.longitude = params[6];
    var jsontext = JSON.stringify(obj);
    return jsontext;
}