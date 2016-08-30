/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
var app = {
    // Application Constructor
    initialize: function () {
        this.bindEvents();
    },
    // Bind Event Listeners
    //
    // Bind any events that are required on startup. Common events are:
    // 'load', 'deviceready', 'offline', and 'online'.
    bindEvents: function () {
        document.addEventListener('deviceready', this.onDeviceReady, false);
    },
    // deviceready Event Handler
    //
    // The scope of 'this' is the event. In order to call the 'receivedEvent'
    // function, we must explicitly call 'app.receivedEvent(...);'
    onDeviceReady: function () {
        app.receivedEvent('deviceready');

        br = "<br />";

        // Get the appInfo DOM element
        // var element = document.getElementById("app-info");
        // element.innerHTML = 'Cordova Version: ' + device.cordova + br +
        // 'Platform: ' + device.platform + br + 'Model: ' + device.model + br +
        // 'OS Version: ' + device.version;
    },
    // Update DOM on a Received Event
    receivedEvent: function (id) {

        console.log('Received Event: ' + id);
    }
};

app.initialize();

function goToSection(sectionName) {
    $("#simulate-" + sectionName).click();
}

function removeWarnings() {
    //firstly remove any old warnings
    $("#warning-message").hide();
    $("#error-message").hide();
    $("#accepted-gift-message").hide();
    $("#gifts-successful-message").hide();
}

function getCategoryNameFromId(categoryId) {
    var nameOfCategory;
    switch (categoryId) {
        case 1:
            nameOfCategory = 'music';
            break;
        case 2:
            nameOfCategory = 'movies';
            break;
        case 3:
            nameOfCategory = 'books';
            break;
        case 4:
            nameOfCategory = 'events';
            break;
    }

    return nameOfCategory;
}

function getCategoryNameFromIdString(categoryId) {
    var nameOfCategory;
    switch (categoryId) {
        case "1":
            nameOfCategory = 'music';
            break;
        case "2":
            nameOfCategory = 'movies';
            break;
        case "3":
            nameOfCategory = 'books';
            break;
        case "4":
            nameOfCategory = 'events';
            break;
    }

    return nameOfCategory;
}


function getCategoryIdFromName(categoryName) {
    var idOfCategory;
    switch (categoryName) {
        case "music":
            idOfCategory = 1;
            break;
        case "movies":
            idOfCategory = 2;
            break;
        case "books":
            idOfCategory = 3;
            break;
        case "events":
            idOfCategory = 4;
            break;
    }

    return idOfCategory;
}

function setShowModal(modalTitle, modalBody, btnDismiss, btnSuccess) {
    var modalSelector = $("#giftcase-modal");
    var modalTitleSelector = $(".modal-title");
    var modalBodySelector = $(".modal-body");
    var btnDismissSelector = $("#giftcase-modal .btn-default");
    var btnSuccessSelector = $("#giftcase-modal .btn-success");

    modalTitleSelector.text(modalTitle);
    modalBodySelector.text(modalBody);
    btnDismissSelector.text(btnDismiss);
    btnSuccessSelector.text(btnSuccess);

    modalSelector.modal("show");
}

function hideModal() {
    $("#giftcase-modal").modal("hide");
}

function successfulMessage(positiveString, modalIdName) {
    var selectorMessage = $(modalIdName + " .message");

    selectorMessage.empty();
    selectorMessage.append(positiveString);
    $(modalIdName).show();

    hideModal();
}

function errorMessage(negativeString) {
    var selectorMessage = $("#error-message .message");

    selectorMessage.empty();
    selectorMessage.append(negativeString);
    $("#error-message").show();

    hideModal();
}

//function that is called when it's clicked on application title
function goToHomePage() {
    window.location.href = "main-page.html";
}

//function that sorts gift by time
function sortByTime(gift, callback) {

    //extract all timestamps from result array
    var giftTimes = [];
    $.each(gift, function (i, result) {
        giftTimes.push(result.sendTime);
    });

    //sort timestamps
    giftTimes.sort(function (a, b) {
        return a - b
    });
    var t, sortedGifts = [];
    $.each(gift, function (z, result) {
        for (var i = 0; i < giftTimes.length; i++) {
            t = giftTimes[i];
            var resultTransaction = result;
            if (resultTransaction.sendTime == t) {
                sortedGifts.push(result);
            }
        }
    });

   callback(sortedGifts);
}