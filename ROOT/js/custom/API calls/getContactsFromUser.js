//constants
var BASE_URL = "http://social.tel.fer.hr:8080/GiftCaseApi/api/";
var EURO = "\u20ac";

getContactsFromUser();

function getContactsFromUser() {

    var contacts = [];
    var loadingSelector = $("#loading-portfolio");

    //get User Id from localStorage
    var userId = window.localStorage.getItem('userId');
    console.log("User Id: " + userId);

    //variable for user location that is used in eventsGifts
    var location;

    if (userId) {

        //make Ajax Call to get contacts from user that was passed
        $.ajax({
            type: "GET",
            dataType: 'json',
            cache: false,
            crossDomain: true,
            url: BASE_URL + "users/" + userId + "/contacts",
            beforeSend: function () {
                loadingSelector.show();
            },
            success: function (jsonObj, textStatus, xhr) {
                //hide loader
                loadingSelector.fadeOut("slow");

                console.log(jsonObj);

                //menage exceptions
                var resultOfException = checkIfExceptionExists(jsonObj);
                if (resultOfException) {
                    menageExceptions(resultOfException, 'getContactsFromUser()');
                }

                //jsonObj is array of contacts from user with given Id
                contacts = jsonObj;

                //get contacts list div from main-page.html
                var contactsListDiv = $('#portfolio-items');

                //array of contactsIds and user location
                var arrayOfContactsIDs = [];
                var arrayOfUserLocation = [];

                $.each(contacts, function (i, result) {
                    //push ids of contacts
                 /*   var contactIds = result.data;

                    //if returned contacts are multiple and they are in array
                    if (contactIds.length > 1) { */

                   /*     $.each(contactIds, function (i, contact) { */
                            arrayOfContactsIDs.push(result.facebookId);
                            if (result.location != "") {
                                arrayOfUserLocation.push(result.location);
                            } else {
                                arrayOfUserLocation.push("undefined");
                            }

                            var profilePicture, appStatus;

                            //for each contact save in variable their info
                            if (result.profilePicture != null) {
                                profilePicture = result.profilePicture;
                            } else {
                                profilePicture = "https://graph.facebook.com/" + result.facebookId + "/picture?type=large";
                            }
                            if (result.location != "") {
                                location = result.location;
                            }
                            if (result.status != null) {
                                appStatus = result.status;

                                //color of circle icon that indicates application status
                                var onlineColor = "#81FE00";
                                var offlineColor = "#F67136";
                                var iconColor = appStatus == "online" ? onlineColor : offlineColor;
                            }

                            var userName = result.first_name + " " + result.last_name;
                            var birthday = result.birthday;

                            appendContactsList(profilePicture, userName, appStatus, iconColor, birthday, location, contactsListDiv, i);
                });


                //click on friend item to get to the categories page
                $("body").click(function (event) {
                    //binding click event to include every part of the list
                    if ($(event.target).attr('class') === 'fa fa-check-square-o friend-id' ||
                        $(event.target).attr('class') === 'link-right friend-id') {

                        //firstly unbind click event on categories
                        $(".send-category-id").unbind("click");

                        //remove any old warnings
                        $("#warning-message").hide();

                        var thisElement = $(event.target);
                        var targetUserCountId = thisElement.attr("id");
                        var targetUserId = arrayOfContactsIDs[targetUserCountId];

                        //set location for user
                        window.localStorage.setItem("location", arrayOfUserLocation[targetUserCountId]);

                        chooseCategoryForTargetUser(targetUserId);
                        //getLocationOfUser(targetUserId);
                    }
                });
            },
            error: function (xhr, textStatus, errorThrown) {
                console.log(xhr.status);
            }
        });
    } else {
        window.location.href = "index.html";
    }
}

function appendContactsList(profilePicture, userName, appStatus, iconColor, birthday, location, contactsListDiv, i) {
    var stringContactsListDiv = "<div class='mix friend' style='display:inline-block;'> " +
        "<figure> " +
        "<div class='img' style='text-align: center;'> " +
        "<img src='" + profilePicture + "' alt='" + userName + "'> " +
        "<div class='overlay'> " +
        "<a href='#' class='link-right friend-id' id='" + i + "'><i id='" + i + "' class='fa fa-check-square-o friend-id'></i></a> " +
        "</div> " +
        "</div> " +
        "<figcaption class='item-description'> ";

    if (appStatus != null && iconColor != null) {
        stringContactsListDiv += "<p class='app-status'><i class='fa fa-circle fa-fw' aria-hidden='true' style='color: " + iconColor + "'></i>&nbsp; " + appStatus + "</p>";
    }

    stringContactsListDiv += "<h4 class='item-title'>" + userName + "</h4> " +
        "<p><span>birthday: </span>" + birthday + "</p>";

    if (location != undefined) {
        stringContactsListDiv += "<p><span>location: </span>" + location + "</p> " +
            "</figcaption> " +
            "</figure> " +
            "</div>";
    }

    contactsListDiv.append(stringContactsListDiv);
}