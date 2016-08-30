//base url of the server that runs GiftCase
var BASE_URI = "http://social.tel.fer.hr:8080/GiftCaseApi/api/";

//parsing current URI to get CODE to send backend 
var code = parseFacebookCallbackUri();

if (code) {

    callBackendFunctionForForwardingCode(code);
} else {

}

function parseFacebookCallbackUri() {

    //get current location and extract code from it
    console.log(window.location.href);

    //code from Redirect URI
    var code;

    var codeUri = window.location.href;
    var codeUriStartIndex = codeUri.indexOf("code");

    if (codeUriStartIndex >= 0) {

        code = codeUri.substring(codeUriStartIndex);

        console.log("Code: " + code);

        return code;
    } else {
        return false;
    }

}

function callBackendFunctionForForwardingCode(code) {

    var userId, facebookId, profilePicture, firstName, lastName, name;

    console.log(BASE_URI+ "Callback?" + code);

    //get userId from server to javascript
    $.ajax({
        type: "GET",
        url: BASE_URI + "Callback?" + code,
        success: function (jsonObj, textStatus, xhr) {
            console.log(jsonObj);
            //   console.log(xhr);

            //menage exceptions
            var resultOfException = checkIfExceptionExists(jsonObj);
            if (resultOfException) {
                menageExceptions(resultOfException, 'callBackendFunctionForForwardingCode(' + code + ')');
            }
            userId = jsonObj.usersId.toString();
            facebookId = jsonObj.facebookId;
            profilePicture = jsonObj.profilePicture;
            firstName = jsonObj.firstName;
            lastName = jsonObj.lastName;

            console.log(userId.toString());

            name = firstName + " " + lastName;

            //save userId in local storage HTML5 -> works with Cordova
            if (typeof (Storage) !== "undefined") {
                window.localStorage.setItem("userId", userId);
                window.localStorage.setItem("facebookId", facebookId);
                window.localStorage.setItem("profilePicture", profilePicture);
                window.localStorage.setItem("nameOfTheUser", name);
            }

           window.location.href = "index.html";
        },
        error: function (xhr, textStatus, errorThrown) {
            console.log(xhr.status);
        }
    });

}