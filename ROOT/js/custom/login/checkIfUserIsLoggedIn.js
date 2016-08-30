$(document).ready(function () {
    //check if localStorage userid is set
    var userId = window.localStorage.getItem('userId');

    //if user already logged in, immediately redirect him
    console.log("Id logiranog usera: " + userId);
    if (userId) {
        window.location.href = "main-page.html";
    }

});