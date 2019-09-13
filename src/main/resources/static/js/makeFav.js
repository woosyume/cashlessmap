function toggleColor() {
    var star = document.getElementById("star");
    if (star.style.fill == "none") {
        star.style.fill = "#FFD700";
    } else {
        star.style.fill = "none";
    }
}
// function requestToFav() {
//     $.ajax({
//         url: "/favorite",
//         type: 'post',
//         dataType: 'json'
//     })
//     .done(function(json){
//         console.log(json);
//     })
//     .fail(function(){
//         alert('noooo');
//     });
// }