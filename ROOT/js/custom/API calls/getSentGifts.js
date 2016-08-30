function getSentGifts() {

    var userId = window.localStorage.getItem("userId");
    var loadingSelector = $("#loading-sent-gifts");

    console.log(BASE_URL + 'users/' + userId + '/sentGifts');

    //ajax that calls REST to get gifts from user of target user
    $.ajax({
        type: "GET",
        dataType: 'json',
        cache: false,
        crossDomain: true,
        url: BASE_URL + 'users/' + userId + '/sentGifts',
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
                menageExceptions(resultOfException, 'getSentGifts()');
            }

            //jsonArray is array of sent gifts
            var sentGifts = jsonArray;
            //get sent gifts list div from main-page.html
            var sentGiftsList = $('#sent-gifts .row');
            sentGiftsList.empty();

            var appendInSentGifts = "";

            //try catch for sent gifts
            if (sentGifts.length > 0) {

                //callback for sorting up sent gifts
                sortByTime(sentGifts, function (sentGiftsCallback) {

                    //foreach of sentGiftsObject append on listview
                    $.each(sentGiftsCallback, function (i, result) {

                        var gift = result;

                        getNameOfUser(gift.recipient, function (name) {
                            console.log(gift);

                            //get name of recipient and add that name to the gift
                            var recipient = name;
                         //   gift.recipient = recipient;

                            appendInSentGifts = "<div class='col-sm-6 col-md-3 wow flipInY' data-wow-offset='10' data-wow-delay='0.2s' id='" + i + "-gift'> ";
                            appendInSentGifts += makeListSentReceived(gift, 'sent', name);

                            //see what kind of status is actual
                            switch (gift.status) {
                                case 'pending':
                                    appendInSentGifts += "<div class='social-icons'> " +
                                        "<ul> " +
                                        "<li style='cursor: default;'><a><i class='fa fa-clock-o'></i><span>Pending on response</span></a></li> " +
                                        "</ul> ";
                                    break;
                                case 'accepted':
                                    appendInSentGifts += "<div class='social-icons'> " +
                                        "<ul> " +
                                        "<li style='cursor: default;'><a><i class='fa fa-thumbs-up'></i><span>Gift accepted</span></a></li> " +
                                        "</ul> ";
                                    break;
                                case 'declined':
                                    appendInSentGifts += "<div class='social-icons'> " +
                                        "<ul> " +
                                        "<li style='cursor: default;'><a><i class='fa fa-thumbs-down'></i><span>Gift declined</span></a></li> " +
                                        "</ul> ";
                                    break;
                            }

                            appendInSentGifts += "</div> " +
                                "</div> " +
                                "</div> " +
                                "</div>";


                            sentGiftsList.append(appendInSentGifts);
                        });
                    });
                });
            } else {
                appendInSentGifts = "<div class='col-sm-12 wow flipInY no-gifts' data-wow-offset='10' data-wow-delay='0.2s' id='" + i + "-gift'>Sorry, there are no sent gifts.</div> ";
                sentGiftsList.append(appendInSentGifts);
            }

            //show sent gifts section
            $("#sent-gifts").show();
            //go to received gifts
            goToSection("sent-gifts");
        },
        error: function (xhr, textStatus, errorThrown) {
            console.log(xhr.status);
        }
    });
}