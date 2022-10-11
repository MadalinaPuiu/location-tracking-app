var map;
var myLatLng = {lat: 46.7693924, lng: 23.5902006};

function initialize() {
    var mapCanvas = document.getElementById('map');
    var mapOptions = {
        center: new google.maps.LatLng(myLatLng),
        zoom: 8,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    }

    map = new google.maps.Map(mapCanvas, mapOptions)
}

function addStaticMarker() {
    var pos = getRandomPosition();

    new google.maps.Marker({
        position: pos,
        map: map,
        title: 'Hello World - 1!'
    });
}

function getRandomPosition() {
    var randLatLng = {
        lat: (myLatLng["lat"] + Math.floor(Math.random() * 5) + 1),
        lng: (myLatLng["lng"] + Math.floor(Math.random() * 5) + 1)
    };
    return randLatLng;
}