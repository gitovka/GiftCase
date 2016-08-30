//function that logouts user from application
function logoutUserFromApp() {

    var userId = window.localStorage.getItem('userId');

    //example of URL http://localhost:8080/SCasePilot/Logout/142243812773943
    //ajax that calls REST to logout user
    $.ajax({
        type: "DELETE",
        dataType: 'json',
        cache: false,
        crossDomain: true,
        url: BASE_URL + 'users/' + userId + "/logout",
        success: function (jsonObj, textStatus, xhr) {

            //different for others exceptions because code that logout is successful is gained by InfoException
            var resultOfException = checkIfExceptionExists(jsonObj);
            if (resultOfException) {
                switch (resultOfException) {
                case 'User is successfuly logged out!':
                    //delete item from localStorage
                    window.localStorage.removeItem('userId');
                    window.localStorage.removeItem('nameOfLoggedUser');
                    window.location.href = 'index.html';
                    break;
                case 'system':
                    errorMessage(" Something went wrong :(");
                    procedureAfterSystemException();
                    break;
                case 'call':
                    procedureAfterApplicationException('logoutUserFromApp()');
                    break;
                }
            }
        },
        error: function (xhr, textStatus, errorThrown) {
            console.log(xhr.status);
        }
    });
}