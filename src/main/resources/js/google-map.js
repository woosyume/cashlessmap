function success(position) {
    var MyLatLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
    var Options = {
        zoom: 15,      //地図の縮尺値
        center: MyLatLng,    //地図の中心座標
        mapTypeId: 'roadmap'   //地図の種類
    };
    var map = new google.maps.Map(document.getElementById('map'), Options);

    function setMarker(lat, lng) {
        var markerLatLng = new google.maps.LatLng(lat, lng);
        var marker = new google.maps.Marker({
            position: markerLatLng,
            map: map
        });
    }

    setMarker(position.coords.latitude, position.coords.longitude);
}

navigator.geolocation.getCurrentPosition(success);
