language = 'en';
$(function(){
    // Set language for creating dynamic url
    $('input[name="lang"]').change(function() {
        language = $(this).val();
        console.log(createJson(collectValuesAsList(language)));
        loadLanguage(language);
        requestToApi();
    })

    $("#visaicon").click(function(){
        if ($("#visaicon").attr("value") == 0){
            $("#visaicon").attr('value', 1);
            var src = 'image/visa.png';
            $("#visa-selected").attr("src", src);
            console.log(createJson(collectValuesAsList(language)));
            clearMarkers();
            requestToApi();
        } else {
            $("#visaicon").attr('value', 0);
            $("#visa-selected").attr("src", "");
            console.log(createJson(collectValuesAsList(language)));
            clearMarkers();
            requestToApi();
        };
    });
    $("#masterscardicon").click(function(){
        if ($("#masterscardicon").attr("value") == 0){
            $("#masterscardicon").attr('value', 1);
            var src = 'image/masterscard.png';
            $("#masterscard-selected").attr("src", src);
            console.log(createJson(collectValuesAsList(language)));
            clearMarkers();
            requestToApi();
        } else {
            $("#masterscardicon").attr('value', 0);
            $("#masterscard-selected").attr("src", "");
            console.log(createJson(collectValuesAsList(language)));
            clearMarkers();
            requestToApi();
        };
    });
    // Set options for creating dynamic url
    $("#rakutenicon").click(function(){
        if ($("#rakutenicon").attr("value") == 0){
            $("#rakutenicon").attr('value', 1);
            var src = 'image/rakutenpay.png';
            $("#rakuten-selected").attr("src", src);
            console.log(createJson(collectValuesAsList(language)));
            clearMarkers();
            requestToApi();
        } else {
            $("#rakutenicon").attr('value', 0);
            $("#rakuten-selected").attr("src", "");
            console.log(createJson(collectValuesAsList(language)));
            clearMarkers();
            requestToApi();
        };
    });
    $("#paypayicon").click(function(){
        if ($("#paypayicon").attr("value") == 0){
            $("#paypayicon").attr("value", 1);
            var src = 'image/paypay.png';
            $("#paypay-selected").attr("src", src);
            console.log(createJson(collectValuesAsList(language)));
            clearMarkers();
            requestToApi();
        } else {
            $("#paypayicon").attr("value", 0);
            $("#paypay-selected").attr("src", "");
            console.log(createJson(collectValuesAsList(language)));
            clearMarkers();
            requestToApi();
        };
    });
    $("#appleicon").click(function(){
        if ($("#appleicon").attr("value") == 0){
            $("#appleicon").attr('value', "1");
            var src = 'image/applepay.png';
            $("#apple-selected").attr("src", src);
            console.log(createJson(collectValuesAsList(language)));
            clearMarkers();
            requestToApi();
        } else {
            $("#appleicon").attr("value", 0);
            $("#apple-selected").attr("src", "");
            console.log(createJson(collectValuesAsList(language)));
            clearMarkers();
            requestToApi();
        };
    });
    $("#wechaticon").click(function(){
        if ($("#wechaticon").attr("value") == 0){
            $("#wechaticon").attr('value', "1");
            var src = 'image/wechat.png';
            $("#wechat-selected").attr("src", src);
            console.log(createJson(collectValuesAsList(language)));
            clearMarkers();
            requestToApi();
        } else {
            $("#wechaticon").attr("value", 0);
            $("#wechat-selected").attr("src", "");
            console.log(createJson(collectValuesAsList(language)));
            clearMarkers();
            requestToApi();
        };
    });
    // Set options for creating dynamic url
    $(".lunch_slct").click(function(){
        if(this.checked){
            $(".lunch_slct").val("1");
            console.log(createJson(collectValuesAsList(language)));
            clearMarkers();
            requestToApi();
        } else {
            $(".lunch_slct").val("0");
            console.log(createJson(collectValuesAsList(language)));
            clearMarkers();
            requestToApi();
        };
    });
    $(".card_slct").click(function(){
        if(this.checked){
            $(".card_slct").val("1");
            console.log(createJson(collectValuesAsList(language)));
            clearMarkers();
            requestToApi();
        } else {
            $(".card_slct").val("0");
            console.log(createJson(collectValuesAsList(language)));
            clearMarkers();
            requestToApi();
        };
    });
    $(".nosmoking_slct").click(function(){
        if(this.checked){
            $(".nosmoking_slct").val("1");
            console.log(createJson(collectValuesAsList(language)));
            clearMarkers();
            requestToApi();
        } else {
            $(".nosmoking_slct").val("0");
            console.log(createJson(collectValuesAsList(language)));
            clearMarkers();
            requestToApi();
        };
    });
    $(".bottomlesscup_slct").click(function(){
        if(this.checked){
            $(".bottomlesscup_slct").val("1");
            console.log(createJson(collectValuesAsList(language)));
            clearMarkers();
            requestToApi();
        } else {
            $(".bottomlesscup_slct").val("0");
            console.log(createJson(collectValuesAsList(language)));
            clearMarkers();
            requestToApi();
        };
    });
    $(".wifi_slct").click(function(){
        if(this.checked){
            $(".wifi_slct").val("1");
            console.log(createJson(collectValuesAsList(language)));
            clearMarkers();
            requestToApi();
        } else {
            $(".wifi_slct").val("0");
            console.log(createJson(collectValuesAsList(language)));
            clearMarkers();
            requestToApi();
        };
    });
    $(".privateroom_slct").click(function(){
        if(this.checked){
            $(".privateroom_slct").val("1");
            console.log(createJson(collectValuesAsList(language)));
            clearMarkers();
            requestToApi();
        } else {
            $(".privateroom_slct").val("0");
            console.log(createJson(collectValuesAsList(language)));
            clearMarkers();
            requestToApi();
        };
    });
});
$(function createJson(params) {
    var obj = new Object();
    obj.lang = params[0];
    obj.pay = params[1];
    obj.lunch = params[2];
    obj.card = params[3];
    obj.nosmoking = params[4];
    obj.bottomlesscup = params[5];
    obj.wifi = params[6];
    obj.privateroom = params[7];
    obj.latitude = params[8];
    obj.longitude = params[9];
    obj.creditCard = params[10];
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
        array_pay.push("楽天ペイ"); // Set rakuten pay as default
    } else {
        if ($('#rakutenicon').attr('value') == 1) array_pay.push("楽天ペイ");
        if ($('#paypayicon').attr('value') == 1) array_pay.push("PayPay");
        if ($('#appleicon').attr('value') == 1) array_pay.push("Apple Pay");
        if ($('#wechaticon').attr('value') == 1) array_pay.push("WeChat Pay");
    }
    array.push(array_pay);
    // Options
    array.push($('.lunch_slct').val());
    array.push($('.card_slct').val());
    array.push($('.nosmoking_slct').val());
    array.push($('.bottomlesscup_slct').val());
    array.push($('.wifi_slct').val());
    array.push($('.privateroom_slct').val());
    // array.push(currentPosition.latitude);
    // array.push(currentPosition.longitude);
    // TODO
    array.push('34.6940735');
    array.push('135.4935862');

    var array_card  = new Array();
    if ($('#visaicon').attr('value') == 1) array_card.push("Visa");
    if ($('#masterscardicon').attr('value') == 1) array_card.push("MasterCard");
    array.push(array_card);

    return array;
}
function createJson(params) {
    var obj = new Object();
    obj.lang = params[0];
    obj.pay = params[1];
    obj.lunch = params[2];
    obj.card = params[3];
    obj.nosmoking = params[4];
    obj.bottomlesscup = params[5];
    obj.wifi = params[6];
    obj.privateroom = params[7];
    obj.latitude = params[8];
    obj.longitude = params[9];
    obj.creditCard = params[10];
    var jsontext = JSON.stringify(obj);
    return jsontext;
}

function requestToApi() {
    var themeName = 'sk-cube-grid';
    var message;

    $.i18n.properties({
        name: 'Messages',
        path: 'bundle/',
        mode: 'both',
        language: language,
        callback: function() {
            message = $.i18n.map.msg_loading;
        }
    });

    HoldOn.open({
        theme: themeName,
        message: message
    });

    $.ajax({
            url: "/navi",
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            data : createJson(collectValuesAsList(language)),
            async: true
        })
        .done(function(json, textStatus, jqXHR){
        	console.log(json);

        	json["stores"].forEach(function(store){
                var latitude = store["latitude"];
                var longitude = store["longitude"];
                var name = store["translatedName"];
                var pr = store["translatedPrShort"];
                var img1 = store["shopImage1"]; //src of img
                var img2 = store["shopImage2"];
                var QR = store["qrCode"];
                var storeId = store["storeId"];
                var tel = store["tel"];
                setMarker(latitude, longitude, name, pr, img1, img2, QR, storeId, tel);
                HoldOn.close();
            })

        }).fail(function(jqXHR, textStatus, errorThrown){
        	alert(createJson(collectValuesAsList(language)))
        	alert('error');
        });
}

function loadLanguage(lang) {
    $.i18n.properties({
        name: 'Messages',
        path: 'bundle/',
        mode: 'both',
        language: lang,
        callback: function() {
            $("#msg_welcome").text($.i18n.map.msg_welcome);
            $("#msg_option_lunch").text($.i18n.map.msg_option_lunch);
            $("#msg_option_card").text($.i18n.map.msg_option_card);
            $("#msg_option_nosmoking").text($.i18n.map.msg_option_nosmoking);
            $("#msg_option_bottomlesscup").text($.i18n.map.msg_option_bottomlesscup);
            $("#msg_option_wifi").text($.i18n.map.msg_option_wifi);
            $("#msg_option_privateroom").text($.i18n.map.msg_option_privateroom);
            $("#msg_menu_pay").text($.i18n.map.msg_menu_pay);
            $("#msg_menu_card").text($.i18n.map.msg_menu_card);
            $("#msg_menu_settings").text($.i18n.map.msg_menu_settings);
            $("#msg_menu_detail").text($.i18n.map.msg_menu_detail);
            $("#msg_search").val($.i18n.map.msg_search);
        }
    });
}

function sleep(waitMsec) {
    var startMsec = new Date();
    while (new Date() - startMsec < waitMsec);
};
document.onkeypress = enter;
function enter(){
  if( window.event.keyCode == 13 ){
    return false;
  }
}
