var language;
$(function(){
    // Set language for creating dynamic url
    $('input[name="lang"]').change(function() {
        language = $(this).val();
        console.log(createJson(collectValuesAsList(language)));
        requestToApi();
    })
                    
    // Set options for creating dynamic url
    $(".rakutenpay").click(function(){
        if(this.checked){
            $(".rakutenpay").val("1");
            console.log(createJson(collectValuesAsList(language)));
            requestToApi();
        } else {
            $(".rakutenpay").val("0");
            console.log(createJson(collectValuesAsList(language)));
            requestToApi();
        };
    });
    $(".paypay").click(function(){
        if(this.checked){
            $(".paypay").val("1");
            console.log(createJson(collectValuesAsList(language)));
            requestToApi();
        } else {
            $(".paypay").val("0");
            console.log(createJson(collectValuesAsList(language)));
            requestToApi();
        };
    });
    $(".applepay").click(function(){
        if(this.checked){
            $(".applepay").val("1");
            console.log(createJson(collectValuesAsList(language)));
            requestToApi();
        } else {
            $(".applepay").val("0");
            console.log(createJson(collectValuesAsList(language)));
            requestToApi();
        };
    });
    // Set options for creating dynamic url
    $(".lunch_slct").click(function(){
        if(this.checked){
            $(".lunch_slct").val("1");
            console.log(createJson(collectValuesAsList(language)));
            requestToApi();
        } else {
            $(".lunch_slct").val("0");
            console.log(createJson(collectValuesAsList(language)));
            requestToApi();
        };
    });
    $(".card_slct").click(function(){
        if(this.checked){
            $(".card_slct").val("1");
            console.log(createJson(collectValuesAsList(language)));
            requestToApi();
        } else {
            $(".card_slct").val("0");
            console.log(createJson(collectValuesAsList(language)));
            requestToApi();
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
        array_pay.push("楽天ペイ"); // Set rakuten pay as default
    } else {
        if ($('.rakutenpay').val() == 1) array_pay.push("楽天ペイ");
        if ($('.paypay').val() == 1) array_pay.push("PayPay");
        if ($('.applepay').val() == 1) array_pay.push("Apple Pay");
    }
    array.push(array_pay);
    // Options
    array.push($('.lunch_slct').val());
    array.push($('.card_slct').val());
    array.push($('.nosmoking_slct').val());
    // TODO : latitude
    array.push("34.662778");
    array.push("135.572867");
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

function requestToApi() {
    $.ajax({
            url: "/navi",
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            data : createJson(collectValuesAsList(language))
        })
        .done(function(data, textStatus, jqXHR){
            console.log(data);
        }).fail(function(jqXHR, textStatus, errorThrown){
            alert('error');
     });           
}
