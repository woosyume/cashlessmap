var MyLatLng = new google.maps.LatLng(34.686766, 135.518479);
var Options = {
    zoom: 15,      //地図の縮尺値
    center: MyLatLng,    //地図の中心座標
    mapTypeId: 'roadmap'   //地図の種類
};
var map = new google.maps.Map(document.getElementById('map'), Options);

var markerPos1 = new google.maps.LatLng(34.686766, 135.518479);
var marker1 = new google.maps.Marker({
    position: markerPos1,
    map: map
});

var markerPos2 = new google.maps.LatLng(34.686766, 135.418479);
var marker2 = new google.maps.Marker({
    position: markerPos2,
    map: map
});

var markerPos3 = new google.maps.LatLng(34.586766, 135.518479);
var marker3 = new google.maps.Marker({
    position: markerPos3,
    map: map
});