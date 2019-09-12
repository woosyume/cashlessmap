var map;

var markers = [];
var currentPosition;

function setMarker(lat, lng, name, pr, img1, img2, QR) {
    var markerLatLng = new google.maps.LatLng(lat, lng, name, pr, img1, img2, QR);
    var marker = new google.maps.Marker({
        position: markerLatLng,
        map: map,
    });
    markers.push(marker);
    marker.addListener("click", function() {
        document.getElementById("storeName").innerHTML = name;
        document.getElementById("PR").innerHTML = pr;
                $('.detail').addClass('open');
                //if(img1=="")
                $(".shopImage1").attr("src", img1);
                $(".shopImage2").attr("src", img2);
                $("#QR").attr("src", "https://c-r.gnst.jp/tool/qr/?id=kc92111&q=6");
        });
        
}


function clearMarkers() {
    for(var i=0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
    markers.push( new google.maps.Marker({
        position: new google.maps.LatLng(currentPosition.latitude, currentPosition.longitude),
        map: map,
        icon: {
            url: "image/glico.png",
            scaledSize: new google.maps.Size(56, 84)
        }
    }));
}

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

function getCurrentPosision() {
    return currentPosition;
}

navigator.geolocation.getCurrentPosition(success);
