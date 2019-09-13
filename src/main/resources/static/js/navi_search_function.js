var searchKeyword;
$(document).ready(function() {
    $('.btn-square').click(function(){
        searchKeyword = $('.searchKeyword').val();
        if (searchKeyword == '') {
            // No process
        } else {
            clearMarkers();

            var themeName = 'sk-cube-grid';
            var message;
    
            $.i18n.properties({ 
                name: 'Messages', 
                path: 'bundle/', 
                mode: 'both', 
                language: language, 
                callback: function() { 
                    message = $.i18n.map.msg_searching + ' : ' + searchKeyword;
                }
            });
    
            HoldOn.open({
                theme: themeName,
                message: message
            });
        
            $.ajax({
                url: "/search",
                type: 'post',
                dataType: 'json',
                contentType: 'application/json',
                data : createJsonForSearch(language, searchKeyword),
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
                    setMarker(latitude, longitude, name, pr, img1, img2, QR, storeId);
                    HoldOn.close();
                })
        
            }).fail(function(jqXHR, textStatus, errorThrown){
                alert('error');
            });
        }
});

function createJsonForSearch(lang, searchKeyword) {
    var obj = new Object();
    obj.lang = lang;
    obj.freeword = searchKeyword;
    var jsontext = JSON.stringify(obj);
    return jsontext;}
})