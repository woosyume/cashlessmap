var map;
var language = 'en';
var markers = [];
var currentPosition;
var directionsService = new google.maps.DirectionsService();
var directionsDisplay = new google.maps.DirectionsRenderer({
    suppressMarkers: true,
    preserveViewport: true
});

function setMarker(lat, lng, name, pr, img1, img2, QR, storeId) {
    var markerLatLng = new google.maps.LatLng(lat, lng, name, pr, img1, img2, QR);
    var marker = new google.maps.Marker({
        position: markerLatLng,
        map: map,
    });
    markers.push(marker);
    marker.addListener("click", function() {
        requestDurationToApi(currentPosition.latitude, currentPosition.longitude, lat, lng);
        setRoute(currentPosition.latitude, currentPosition.longitude, lat, lng);

        $('.detail').addClass('open');
        // Request to translate details for clicked merchant.
        $.ajax({
            url: "/international",
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            data : createInternationalJson(language, storeId),
            async: true
        })
        .done(function(json, textStatus, jqXHR){
            console.log(JSON.stringify(json));

            var name, storeId;
        	json["stores"].forEach(function(store){
                name = store["name"];
                storeId = store["translatedPrShort"];
            })
            document.getElementById("storeName").innerHTML = name;
            document.getElementById("storeName").style.color = 'navy';
            document.getElementById("storeName").style.borderBottom = 'dashed 2px navy';
            document.getElementById("PR").innerHTML = storeId;

            var defaultImage = 'image/default.jpeg';
            if (img1 != '' || img2 != '') {
                $(".shopImage1").attr("src", img1);
                $(".shopImage2").attr("src", img2);
            } else {
                $(".shopImage1").attr("src", defaultImage);
                $(".shopImage2").attr("src", defaultImage);
            }
            $(".QR").attr("src", QR);

        }).fail(function(jqXHR, textStatus, errorThrown){
            alert('error');
        });
    });
}

function createInternationalJson(language, storeId) {
    var obj = new Object();
    obj.lang = language;
    obj.storeId = storeId;
    var jsontext = JSON.stringify(obj);
    return jsontext;
}

function clearMarkers() {
    clearRoute();
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

function requestDurationToApi(fromLat, fromLng, toLat, toLng) {
	var json = {
		    fromLatitude: fromLat,
		    fromLongitude: fromLng,
		    toLatitude: toLat,
		    toLongitude: toLng
	}

	$.ajax({
	    url: "/map/duration",
	    type: 'post',
	    dataType: 'json',
	    contentType: 'application/json',
	    data : JSON.stringify(json)
	})
	.done(function(json, textStatus, jqXHR){
		//console(json["duration"]);
	    document.getElementById("time").innerHTML = json["duration"];
	}).fail(function(jqXHR, textStatus, errorThrown){
	});
}

function setRoute(fromLat, fromLng, toLat, toLng) {
	var origin = new google.maps.LatLng(fromLat, fromLng) ;
	var distination = new google.maps.LatLng(toLat, toLng) ;
	var request = {
		origin: origin,
		destination: distination,
		travelMode: google.maps.DirectionsTravelMode.WALKING,
	};

	directionsDisplay.setMap(map);
	directionsService.route(request, function(response,status){
		if (status == google.maps.DirectionsStatus.OK){
			directionsDisplay.setDirections(response);
		}
	});
}

function clearRoute() {
	directionsDisplay.setMap(null);
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
