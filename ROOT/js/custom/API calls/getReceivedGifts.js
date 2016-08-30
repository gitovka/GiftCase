function getReceivedGifts() {

    var userId = window.localStorage.getItem("userId");
    var loadingSelector = $("#loading-received-gifts");

   // console.log(BASE_URL + 'users/' + userId + '/receivedGifts');

    //ajax that calls REST to get gifts from user of target user
    $.ajax({
        type: "GET",
        dataType: 'json',
        cache: false,
        crossDomain: true,
        url: BASE_URL + 'users/' + userId + '/receivedGifts',
        beforeSend: function () {
            loadingSelector.show();
        },
        success: function (jsonArray, textStatus, xhr) {
            console.log(jsonArray);

            //hide loader
            loadingSelector.fadeOut("slow");

            //menage exceptions
            var resultOfException = checkIfExceptionExists(jsonArray);
            if (resultOfException) {
                menageExceptions(resultOfException, 'getReceivedGifts()');
            }

            //jsonArray is array of received gifts
            var receivedGifts = jsonArray;
            //get received gifts list div from main-page.html
            var receivedGiftsList = $('#received-gifts .row');
            receivedGiftsList.empty();

            var appendInReceivedGifts = "";

            //try catch for received gifts
            if (receivedGifts.length > 0) {

                //sort receivedGifts by sendTime
                sortByTime(receivedGifts, function (receivedGiftsCallback) {

                    var countOfUnAnsweredGifts = 0;

                    //foreach of receivedGiftsObject append on listview
                    $.each(receivedGiftsCallback, function (i, result) {

                        //json array parsing on 2 objects
                        var gift = result;

                        //callback function for name of the user who sent gift
                        getNameOfUser(gift.sender, function (name) {

                            //add id of gift
                            gift.id = i;
                        //    gift.sender = name;

                            appendInReceivedGifts = "";

                            appendInReceivedGifts = "<div class='col-sm-6 col-md-3 wow flipInY' data-wow-offset='10' data-wow-delay='0.2s' id='" + i + "-gift'> ";
                            appendInReceivedGifts += makeListSentReceived(gift, 'received', name);

                            if (gift.description != null && gift.description != "") {
                                gift.description = null;
                            }

                            switch (gift.status) {
                                case 'pending':
                                    appendInReceivedGifts += "<div class='social-icons'> " +
                                        "<ul> " +
                                        "<li onclick='sureToAcceptGift(" + JSON.stringify(gift) + ")'><a><i class='fa fa-thumbs-up'></i><span>Accept gift</span></a></li> " +
                                        "<li onclick='sureToDeclineGift(" + JSON.stringify(gift) + ")'><a><i class='fa fa-thumbs-down'></i><span>Decline gift</span></a></li> " +
                                        "</ul> ";
                                    countOfUnAnsweredGifts++;
                                    break;
                                case 'accepted':
                                    appendInReceivedGifts += "<div class='social-icons'> " +
                                        "<ul> " +
                                        "<li style='cursor: default;'><a><i class='fa fa-thumbs-up'></i><span>Gift accepted</span></a></li> " +
                                        "</ul> ";
                                    break;
                                case 'declined':
                                    appendInReceivedGifts += "<div class='social-icons'> " +
                                        "<ul> " +
                                        "<li style='cursor: default;'><a><i class='fa fa-thumbs-down'></i><span>Gift declined</span></a></li> " +
                                        "</ul> ";
                                    break;
                            }

                            appendInReceivedGifts += "</div> " +
                                "</div> " +
                                "</div> " +
                                "</div>";

                            receivedGiftsList.append(appendInReceivedGifts);
                        });
                    });
                });

            } else {
                appendInReceivedGifts = "<div class='col-sm-12 wow flipInY no-gifts' data-wow-offset='10' data-wow-delay='0.2s' id='" + i + "-gift'>Sorry, there are no received gifts.</div> ";
                receivedGiftsList.append(appendInReceivedGifts);
            }

            //show received gifts section
            $("#received-gifts").show();
            //go to received gifts
            goToSection("received-gifts");
        },
        error: function (xhr, textStatus, errorThrown) {
            console.log(xhr.status);
        }
    });
}

//open popup in which user must confirm that he wants to receive that gift
function sureToAcceptGift(gift) {
  /*  console.log("gift from: ");
    console.log(gift.sender);*/

    setShowModal("Accept gift", "Are you sure you want to accept gift?", "No, thanks", "Yes, I'm sure");
    $(".modal-footer .btn-success").attr("onclick", 'acceptGift(' + JSON.stringify(gift) + ')');
}

//open popup in which user must confirm that he wants to decline that gift
function sureToDeclineGift(gift) {
    console.log("gift to decline: ");
    console.log(gift);

    setShowModal("Decline gift", "Are you sure you want to decline gift?", "No, thanks", "Yes, I'm sure");
    $(".modal-footer .btn-success").attr("onclick", 'declineGift(' + JSON.stringify(gift) + ')');
}

function declineGift(gift) {
    //post new status about declination of gift
    var status = new Object();
    status.giftId = gift.giftId;
    status.status = "declined";
    status.sendTime = gift.sendTime;

    postGiftStatus(status, gift.categoryId);
    getReceivedGifts();
}

function acceptGift(gift) {
    var status = new Object();
    status.giftId = gift.giftId;
    status.status = "accepted";
    status.sendTime = gift.sendTime;

    postGiftStatus(status, gift.categoryId);
    getReceivedGifts();
}
