var language;
$(function(){
    // Set language for creating dynamic url
    $('input[name="lang"]').change(function() {
        language = $(this).val();
        console.log(createJson(collectValuesAsList(language)));
        requestToApi();
    })
                    
    // Set options for creating dynamic url
    $("#rakutenicon").click(function(){
        if ($("#rakutenicon").attr("value") == 0){
            $("#rakutenicon").attr('value', 1);
            var src = $(this).children('img').attr('src');
            $("#rakuten-selected").attr("src", src);
            console.log(createJson(collectValuesAsList(language)));
            requestToApi();
        } else {
            $("#rakutenicon").attr('value', 0);
            $("#rakuten-selected").attr("src", "");
            console.log(createJson(collectValuesAsList(language)));
            requestToApi();
        };
    });
    $("#paypayicon").click(function(){
        if ($("#paypayicon").attr("value") == 0){
            $("#paypayicon").attr("value", 1);
            var src = $(this).children('img').attr('src');
            $("#paypay-selected").attr("src", src);
            console.log(createJson(collectValuesAsList(language)));
            requestToApi();
        } else {
            $("#paypayicon").attr("value", 0);
            $("#paypay-selected").attr("src", "");
            console.log(createJson(collectValuesAsList(language)));
            requestToApi();
        };
    });
    $("#appleicon").click(function(){
        if ($("#appleicon").attr("value") == 0){
            $("#appleicon").attr('value', "1");
            var src = $(this).children('img').attr('src');
            $("#apple-selected").attr("src", src);
            console.log(createJson(collectValuesAsList(language)));
            requestToApi();
        } else {
            $("#appleicon").attr("value", 0);
            $("#apple-selected").attr("src", "");
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
            console.log(createJson(collectValuesAsList(language)));
            requestToApi();
        } else {
            $(".nosmoking_slct").val("0");
            console.log(createJson(collectValuesAsList(language)));
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
    if ($('#rakutenicon').attr('value') == 0 &&
     $('#paypayicon').attr('value') == 0 &&
     $('#appleicon').attr('value') == 0) {
        array_pay.push("rakutenpay"); // Set rakuten pay as default
    } else {
        if ($('#rakutenicon').attr('value') == 1) array_pay.push("rakutenpay");
        if ($('#paypayicon').attr('value') == 1) array_pay.push("paypay");
        if ($('#appleicon').attr('value') == 1) array_pay.push("applepay");
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
    obj.card = params[3];
    obj.nosmoking = params[4];
    obj.latitude = params[5];
    obj.longitude = params[6];
    var jsontext = JSON.stringify(obj);
    return jsontext;
}

function requestToApi() {
    var themeName = 'sk-dot';
    HoldOn.open({
        theme: themeName,
        message:"<h4>検索中です。しばらくお待ちください。</h4>"
    });

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
     HoldOn.close();
}