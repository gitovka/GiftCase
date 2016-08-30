//count of gifts for showing constant, initial value is 10
var COUNT = "10";
//MAX price for gifts, initial value is null or MAX NUMBER
var MAX_PRICE = null;
//location of target user from facebook initialised on null
var LOCATION = null;

//put filter before every gift recommendation
function appendFilterForGifts(idFilter, targetUserId, categoryId) {

    /* price filter only for music, movies and books it is already in DOM */

    //check what value is selected and send that value as maxPrice from price range slider
    var sliderSelector = $(".points");
/*
    sliderSelector.slider({
        defaults: true,
        stop: function (event, ui) {
            MAX_PRICE = event.target.value;
            makeUrlToGetGifts(targetUserId, categoryId, null, MAX_PRICE + "#$%#", COUNT);
            sliderSelector.slider("refresh");
        }
    });
*/

    /* css */
    //slider form selector
    var sliderForm = $(".ui-body-c, .ui-page-theme-c .ui-body-inherit, html .ui-bar-c .ui-body-inherit, html .ui-body-c .ui-body-inherit, html body .ui-group-theme-c .ui-body-inherit, html .ui-panel-page-container-c");

    sliderForm.css("margin", "0", 'important');
    sliderForm.css("border", "none", 'important');

    /*/ price filter */


    /* count filter */
    //id of filter where it needs to be
    var idOfFilterHTML = $(idFilter);

    //first empty filter because of append
    var countFilterSelector = $(".count-filter");
    if (countFilterSelector.length) {
        countFilterSelector.empty();
        countFilterSelector.remove();
    }

    //append count filter to every page
    var countFilter = '<div class="count-filter">' +
        '<a href="#" class="count-link" id="5-link">5</a>&nbsp;&nbsp;<a href="#" class="count-link" id="10-link">10</a>&nbsp;&nbsp;<a href="#" class="count-link" id="20-link">20</a>' +
        '</div>';

    idOfFilterHTML.append(countFilter);

    //remove underline on number of gifts
    $("#" + COUNT + "-link").addClass("no-underline");

    //bind click event on count link to filter gifts, number of gifts is inner html of a elements
    var countLinkClass = $(".count-link");
    countLinkClass.on("click", function (e) {
        var element = $(this);
        COUNT = element.html();
        makeUrlToGetGifts(targetUserId, categoryId, LOCATION, MAX_PRICE + "#$%#", COUNT);

        //first put everyone underline
        countLinkClass.addClass("underline");

        e.preventDefault();
    });

    /* /count filter */


    /* location filter only for events */
    /* inputLocationSelector.val(LOCATION);
     */
    //on submit send location
    $("#events-location-form").unbind('submit').submit(function (event) {
        //event.preventDefault();
        var inputLocationSelector = $("#input-location");

        /*  LOCATION = window.localStorage.getItem("location");*/
        LOCATION = inputLocationSelector.val();
        console.log("Location: " + LOCATION);
        if (LOCATION.length != 0) {
            makeUrlToGetGifts(targetUserId, categoryId, LOCATION, null, COUNT);
        } else {
            var warningMessage = "<p class='warning-message'>Please write city for search</p>";
            appendInPopup(warningMessage);
        }

        event.preventDefault();
    });

    /* /location filter only for events */
}