var callFunction = 0;

function checkIfExceptionExists(jsonObj) {
    var resultOfException;

    for (var key in jsonObj) {
        if (key.indexOf("Exception") >= 0) {
            resultOfException = checkException(jsonObj);
        }
        break;
    }

    return resultOfException;
}

//function that checks exceptions from AJAX calls 
function checkException(exception) {
    var resultOfException;

    if (exception.hasOwnProperty('InfoException')) {
        resultOfException = exception.InfoException;
    }
    if (exception.hasOwnProperty('SystemException')) {
        resultOfException = 'system';
    }
    if (exception.hasOwnProperty('ApplicationException')) {
        resultOfException = 'call';
    }
    if (exception.hasOwnProperty('TokenExpiredException')) {
        resultOfException = 'logout';
    }

    return resultOfException;
}

function menageExceptions(resultOfException, functionString) {
    switch (resultOfException) {
    case 'system':
        procedureAfterSystemException();
        break;
    case 'call':
        procedureAfterApplicationException(functionString);
        break;
    case 'logout':
        procedureAfterTokenExpiredException();
    }
}

//after system exception write error and redirect to index.html
function procedureAfterSystemException() {
    errorMessage("Something went wrong :(");

    //redirect to index.html
    setTimeout(function () {
        window.localStorage.removeItem('userId');
        window.location.href = 'index.html';
    }, 1500);
}

//after application exception that function should be called 3 times and then call procedureAfterSystemException
function procedureAfterApplicationException(functionString) {

    if (callFunction > 2) {
        procedureAfterSystemException();
    }

    //ime odnosno string funkcije poziva opet funkcija
    eval(functionString);
    callFunction++;
}

function procedureAfterTokenExpiredException() {
    //show popup of exception
    $('.ok-button').hide();
    $('.informative-message').hide();
    showRedExceptionMessage();
    showException('Token expired. Please login again.');

    //redirect to index.html
    setTimeout(function () {
        logoutUserFromApp();
    }, 1500);
}

function showRedExceptionMessage() {
    $('#exception-message').css("color", "#D87B7B");
    $('#exception-message').css("margin-top", "1.2em");
}