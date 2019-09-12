$(document).ready(function() {
    var src = 'image/rakutenpay.png';
    $("#rakuten-selected").attr("src", src);

    var defaultLang='en';
    loadLanguage(defaultLang);
});

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