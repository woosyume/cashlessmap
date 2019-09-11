var map;

var markers = [];

function setMarker(lat, lng) {
    var markerLatLng = new google.maps.LatLng(lat, lng);
    var marker = new google.maps.Marker({
        position: markerLatLng,
        map: map
    });
    markers.push(marker);
    marker.addListener("click", function() {
        alert("店の情報");
    });
}


function clearMarkers() {
    for(var i=0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
}

function success(position) {
    var MyLatLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
    var Options = {
        zoom: 15,      //地図の縮尺値
        center: MyLatLng,    //地図の中心座標
        mapTypeId: 'roadmap',   //地図の種類
        disableDefaultUI: true
    };
    map = new google.maps.Map(document.getElementById('map'), Options);

    setMarker(position.coords.latitude, position.coords.longitude);
}

navigator.geolocation.getCurrentPosition(success);
