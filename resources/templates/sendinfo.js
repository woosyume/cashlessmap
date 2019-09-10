function sendinfo() {
    var assoc = {};
    //セレクトボックスの値を変数に保存(pay)
    assoc.pay = $('[name=pay]').val();
    console.log(assoc.pay);

    //ラジオボタンの値を変数に保存(language)
    assoc.lang = $('[name=lang]:checked').val();
    console.log(assoc.lang);

    assoc.description = $('[name=lunch]:checked')
    //選択されたチェックボックスの値を配列に保存
    assoc.knownby = $('[name=knownby]:checked').map(function() {
        return $(this).val();
    }).get();
    console.log(assoc.knownby);

    data = JSON.stringify( assoc );

    var xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = function()
    {
        var READYSTATE_COMPLETED = 4;
        var HTTP_STATUS_OK = 200;

        if( this.readyState == READYSTATE_COMPLETED
        && this.status == HTTP_STATUS_OK )
        {
            // レスポンスの表示
            alert( this.responseText );
            $('[name=result]').val(this.responseText);
        }
    }

    xmlHttpRequest.open( 'POST', 'chkjson.php',false );

    // サーバに対して解析方法を指定する
    xmlHttpRequest.setRequestHeader( 'Content-Type', 'application/json');

    // データをリクエスト ボディに含めて送信する
    xmlHttpRequest.send( JSON.stringify(data) );

}