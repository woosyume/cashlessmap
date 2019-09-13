function success(position) {
    var MyLatLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude)
    var Options = {
        zoom: 15,      //地図の縮尺値
        center: MyLatLng,    //地図の中心座標
        mapTypeId: 'roadmap',   //地図の種類
        disableDefaultUI: true
    };
    currentPosition = position.coords;
    map = new google.maps.Map(document.getElementById('map'), Options);

    markers.push( new google.maps.Marker({
        position: new google.maps.LatLng(currentPosition.latitude, currentPosition.longitude),
        map: map,
        icon: {
            url: "image/glico.png",
            scaledSize: new google.maps.Size(56, 84)
        }
    }));
}
navigator.geolocation.getCurrentPosition(success);
sleep(1500);

$(document).ready(function() {
    var src = 'image/rakutenpay.png';
    $("#rakuten-selected").attr("src", src);

    // currentPosition
    // sleep(1500);
    
    requestToApi();
    var defaultLang='en';
    loadLanguage(defaultLang);
});
$(".batsu").on('click', function(){
    $('.batsu').removeClass('active');
    $('.detail').removeClass('open');
})

$('.menu-trigger').on('click',function(){
    if($(this).hasClass('active')){
        $(this).removeClass('active');
        $('nav').removeClass('open');
        $('.overlay').removeClass('open');
    } else {
        $(this).addClass('active');
        $('nav').addClass('open');
        $('.overlay').addClass('open');
    }
    });
    $('.overlay').on('click',function(){
    if($(this).hasClass('open')){
        $(this).removeClass('open');
        $('.menu-trigger').removeClass('active');
        $('nav').removeClass('open');      
    }
});
