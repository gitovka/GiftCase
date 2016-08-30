function showException(exception) {
    console.log("Exception is: " + exception);

    var warningSelector = $("#warning-message");

    $("#warning-message .message").text(exception);
    warningSelector.show();

    $("#simulate-friends").click();
}