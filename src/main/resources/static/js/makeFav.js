function toggleColor() {
    var star = document.getElementById("star");
    if (star.style.fill == "none") {
        star.style.fill = "yellow";
        requestToFav();
    } else {
        star.style.fill = "none";
        requestToFav();
    }
}
// TODO favorite function
function requestToFav() {
    $.ajax({
        url: "/favorite",
        type: 'post',
        dataType: 'json'
    })
    .done(function(json){
        console.log(json);
    })
    .fail(function(){

    });
}