function getUserId() {

    var baseUrl = "http://social.tel.fer.hr:8080/GiftCaseApi/api/";

    //ajax that calls REST to get UserId
    $.ajax({
        type: "GET",
        dataType: 'json',
        cache: false,
        crossDomain: true,
        url: baseUrl + "signin",
        beforeSend: function () {
            console.log("started");
        },
        success: function (jsonObj, textStatus, xhr) {
            console.log("response: " + jsonObj.facebookRedirect);

            //menage exceptions
            var resultOfException = checkIfExceptionExists(jsonObj);
            if (resultOfException) {
                menageExceptions(resultOfException, 'getUserId()');
            }

            //value of JSON that represents URL that will reproduce json of user
            var facebookRedirect = jsonObj.facebookRedirect;

            //in catchFacebookLogin.js
            callFacebookOAuth(facebookRedirect);
        },
        error: function (xhr, textStatus, errorThrown) {
            console.log(xhr.status);
        }
    });

    console.log("finished");
}

function callFacebookOAuth(facebookRedirect) {

    //call Facebook redirect
    window.location.href = facebookRedirect;
}