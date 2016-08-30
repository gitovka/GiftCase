function postGiftProposal(gift) {

    console.log("Gift that is sent: ");
    console.log(JSON.stringify(gift));

    var postGiftDefinedByCategory; //gift post url

    ///users/{usersId}/buyingMusicMovieGift
    //define part of url based on category
    switch (gift.categoryId) {
        case 1:
            postGiftDefinedByCategory = "buyingMusicMovieGift";
            break;
        case 2:
            postGiftDefinedByCategory = "buyingMusicMovieGift";
            break;
        case 3:
            postGiftDefinedByCategory = "buyingBookGift";
            break;
        case 4:
            postGiftDefinedByCategory = "buyingEventGift";
            break;
    }

    console.log(BASE_URL + 'users/' + userId + "/" + postGiftDefinedByCategory);
    //  console.log(JSON.stringify(gift));

    //ajax that calls REST to post gifts that user wants to buy for target user
    $.ajax({
        type: "POST",
        dataType: 'json',
        cache: false,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        crossDomain: true,
        url: BASE_URL + 'users/' + userId + "/" + postGiftDefinedByCategory,
        beforeSend: function () {
            $(".modal-footer .btn-success").hide();
            $(".modal-footer .btn-default").hide();
            $(".modal-footer").append("<div class='spinner-modal' style='background: url(../../../img/spinner-modal.gif) no-repeat center center;height:40px;'></div>")
        },
        data: JSON.stringify(gift),
        success: function (jsonObject, textStatus, xhr) {
            console.log("success");
            $(".modal-footer .btn-success").show();
            $(".modal-footer .btn-default").show();
            $(".spinner-modal").hide();

            //menage exceptions
            var resultOfException = checkIfExceptionExists(jsonObject);
            if (resultOfException) {
                menageExceptions(resultOfException, 'postGiftProposal(' + JSON.stringify(gift) + ')');
            }

            if (resultOfException === "Gift is sent!") {
                console.log("giftIsSent");
                successfulMessage("You successfully sent the gift.", "#gifts-successful-message");

                location.href = "#gifts";
            }
        },
        error: function (xhr, textStatus, errorThrown) {
            console.log(xhr.status);
            errorMessage("Error while sending gift!");
            menageExceptions('ApplicationException', 'postGiftProposal(' + JSON.stringify(gift) + ')');
        }
    });
}

function postGiftStatus(status, categoryId) {

    /*  console.log(BASE_URL + 'users/' + userId + "/status?categoryId=" + categoryId);
     console.log(JSON.stringify(status));*/

    //ajax that calls REST to post status about accepted or declined gift
    $.ajax({
        type: "POST",
        dataType: 'json',
        cache: false,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        crossDomain: true,
        url: BASE_URL + 'users/' + userId + "/status?categoryId=" + categoryId,
        beforeSend: function () {
        },
        data: JSON.stringify(status),
        success: function (jsonObject, textStatus, xhr) {

            //menage exceptions
            var resultOfException = checkIfExceptionExists(jsonObject);
            if (resultOfException) {
                menageExceptions(resultOfException, 'postGiftStatus(' + JSON.stringify(status) + ')');
            }

            var message;
            if (status.status === "accepted") {
                message = "accepted";
            } else {
                message = "declined";
            }

            successfulMessage("You successfully " + message + " gift!", "#accepted-gift-message");
            location.href = "#received-gifts";
        },
        error: function (xhr, textStatus, errorThrown) {
            console.log(xhr.status);
            errorMessage("Error while accepting gift!");
        }
    });

}