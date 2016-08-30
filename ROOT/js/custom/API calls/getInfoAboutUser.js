function getLocationOfUser(userId) {

    //make Ajax Call to get info (name, location, hometown) of user by id
    var jqxHr = $.ajax({
        type: "GET",
        dataType: 'json',
        cache: false,
        crossDomain: true,
        url: BASE_URL + 'users/' +userId + "/info",
        success: function (jsonObj, textStatus, xhr) {
            console.log(jsonObj);
            //menage exceptions
            var resultOfException = checkIfExceptionExists(jsonObj);
            if (resultOfException) {
                menageExceptions(resultOfException, 'getLocationOfUser()');
            }

            var locationOfUser = xhr.responseJSON.location;
            if (locationOfUser != null) {
                locationOfUser = locationOfUser.split(',')[0];
                locationUser = locationOfUser;
            } else {
                var homeTown = xhr.responseJSON.hometown;
                if (homeTown != null) {
                    homeTown = homeTown.split(',')[0];
                    locationUser = homeTown;
                } else {
                    locationUser = "Location of user cannot be determined";
                }
            }

            console.log("location: " + locationUser);
            window.localStorage.setItem("location", locationUser);

        },
        error: function (xhr, textStatus, errorThrown) {
            console.log(xhr.status);
        }
    });
}

function getNameOfUser(facebookId, callback) {

    var userId = window.localStorage.getItem('userId');
    var name;

   // console.log(BASE_URL + 'users/' + userId + "/info?targetFacebookId=" + facebookId);

    var jqxHr = $.ajax({
        type: "GET",
        dataType: 'json',
        cache: false,
        crossDomain: true,
        async: false,
        url: BASE_URL + 'users/' + userId + "/info?targetFacebookId=" + facebookId,
        success: function (jsonObj, textStatus, xhr) {
            //menage exceptions
            var resultOfException = checkIfExceptionExists(jsonObj);
            if (resultOfException) {
                menageExceptions(resultOfException, 'getNameOfUser()');
            }

            var name = xhr.responseJSON.first_name + " " + xhr.responseJSON.last_name;
          //  console.log(name);
            callback(name);
        },
        error: function (xhr, textStatus, errorThrown) {
            console.log(xhr.status);
        }
    });
}

function getProfilePictureAndAppStatus (userId, callback) {

    console.log("Info: " + userId);

    var profilePicture;
    var appStatus;

    var jqxHr = $.ajax({
        type: "GET",
        dataType: 'json',
        cache: false,
        crossDomain: true,
        url: BASE_URL + 'users/' +userId + "/info",
        success: function (jsonObj, textStatus, xhr) {
            //menage exceptions
            var resultOfException = checkIfExceptionExists(jsonObj);
            if (resultOfException) {
                menageExceptions(resultOfException, 'getNameOfUser()');
            }

            profilePicture = xhr.responseJSON.profilePicture;
            appStatus = xhr.responseJSON.applicationStatus;
            
            var returnProfPicAndAppStatus = profilePicture + "####" + appStatus;
            callback(returnProfPicAndAppStatus);
        },
        error: function (xhr, textStatus, errorThrown) {
            console.log(xhr.status);
        }
    });
}