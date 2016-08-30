function makeUrlToGetGifts(targetUserId, categoryId, firstParameter, secondParameter, count, startIndex) {

    var userId = window.localStorage.getItem('userId');
    var getGiftsFromCategory; //part of url that defines category

    // /users/{usersId}/getMusicSocial?targetFacebookId={targetFacebookId}

    //show gifts section
    $("#gifts").show();
    //go to gifts section
    goToSection("gifts");

    //initialize getGiftsFromCategory
    switch(categoryId) {
        case "1":
            getGiftsFromCategory = "getMusicSocial";
            break;
        case "2":
            getGiftsFromCategory = "getMoviesSocial";
            break;
        case "3":
            getGiftsFromCategory = "getBooksSocial";
            break;
        case "4":
            getGiftsFromCategory = "getEventsSocial";
            break;
    }

    //base url
    var url = BASE_URL + "users/" + userId + "/" + getGiftsFromCategory + "?" + "targetFacebookId=" + targetUserId;
    var loadingSelector = $("#loading-gifts");

    //parameters of url
    if (firstParameter != null) {
        if (checkIfPositiveInteger(firstParameter)) {
            url += "&priceMin=" + firstParameter;
        } else {
            url += "&location=" + encodeURLToMatchEventfulFormat(firstParameter);
        }
    }
    if (secondParameter != null) {
        if (secondParameter.indexOf("#$%#") >= 0) {
            secondParameter = secondParameter.slice(0, -4);
            if (secondParameter != "null") {
                url += "&priceMax=" + secondParameter;
            }
        } else {
            url += "&radius=" + secondParameter;
        }
    }

    if (count != null) {
        url += "&count=" + count;
    }
    if (startIndex != null) {
        url += "&startIndex=" + startIndex;
    }

    console.log(url);

    //ajax that calls REST to get gifts from user of target user
    $.ajax({
        type: "GET",
        dataType: 'json',
        cache: false,
        crossDomain: true,
        url: url,
        beforeSend: function () {
            //remove previous gifts
            $(".recommended-gifts").hide();

            loadingSelector.show();
        },
        success: function (jsonArray, textStatus, xhr) {
            console.log(jsonArray);

            //hide loader
            loadingSelector.fadeOut("slow");

            //menage exceptions
            var resultOfException = checkIfExceptionExists(jsonArray);
            if (resultOfException) {
                menageExceptions(resultOfException, 'makeUrlToGetGifts(' + targetUserId + ',' + categoryId + ')');
            }

            if (typeof jsonArray === 'undefined') {
                showException("Category is not available yet. Please, be patient!");
            } else {
                if (!jsonArray.hasOwnProperty('InfoException')) {
                    showRecommandation(categoryId, jsonArray, userId, targetUserId);
                } else {
                    showException(jsonArray.InfoException);
                }
            }
        },
        error: function (xhr, textStatus, errorThrown) {
            console.log(xhr.status);
        }
    });
}

//eventful url should be in format "new-york" not "new+york" or "new%20york"
function encodeURLToMatchEventfulFormat(URL) {
    //remove all spaces with one space and replace that one space with dash
    URL = URL.replace(/\s\s+/g, ' ').replace(' ', '-');
    return URL;
}

//checks if value is integer
function checkIfPositiveInteger(value) {
    if (Math.floor(value) == value && $.isNumeric(value) && value > 0) {
        return true;
    }
    return false;
}
