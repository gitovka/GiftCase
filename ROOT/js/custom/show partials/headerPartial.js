//get profilePicture from localStorage
var profilePictureUrl = window.localStorage.getItem("profilePicture");
var userId = window.localStorage.getItem("userId");
var name = window.localStorage.getItem("nameOfTheUser");

//put header in place for it
var scaseHeaderSelector = $('.scase-header');
scaseHeaderSelector.empty();
scaseHeaderSelector.append(
    '<a href="#" class="back-button" data-rel="back" data-icon="arrow-l"></a>' +
    '<h1 onclick="goToHomePage()" id="title"><img src="../../../img/gift-case-logo.png" class="image-title-of-app"/>GiftCase</h1>' +
    '<p id="name-of-user">' + name +'</p>' +
    '<div class="click-nav"> ' +
    '<ul class="no-js"> ' +
    '<li> ' +
    '<a href="#" class="user"></a> ' +
    '<ul class="dropdown-content"> ' +
    '<li><a href="#received-gifts-page" onclick="getReceivedGifts()"><img src="../../../img/received-gift-icon.png" alt="Icon">Inbox&nbsp;&nbsp;</a></li> ' +
    '<li><a href="#sent-gifts-page" onclick="getSentGifts()"><img src="../../../img/sent-gift-icon.png" alt="Icon">Sentbox&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li> ' +
    '<li><a href="" onclick="loadLogoutInPopup()"><img src="../../../img/exit-icon.png" alt="Icon">Logout &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li> ' +
    '</ul> ' +
    '</li> ' +
    '</ul> ' +
    '</div>'
)
;


//contacts page shouldn't have back button
$("#contacts-page .back-button").hide();

//put user profile picture
$('.user').css('background-image', 'url(' + profilePictureUrl + ')');

//logout
function loadLogoutInPopup() {
    setShowModal("Logout from the application", "Please, be sure you want to logout from the application", "No, thanks", "Yes I'm sure");

    $(".modal-footer .btn-success").attr("onclick", 'logoutUserFromApp()');
}

//dropdown handler
$(function () {
    $('.click-nav > ul').toggleClass('no-js js');
    $('.click-nav .js ul').hide();
    $('.click-nav .js').click(function (e) {
        $('.click-nav .js ul').slideToggle(200);
        $('.clicker').toggleClass('active');
    });
});

