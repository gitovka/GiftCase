var POPUP = "#popup-id";

function showRecommandation(categoryId, gifts, userId, targetUserId) {

    var typeOfGift;

    //name of the type written in lowercase
    typeOfGift = getCategoryNameFromIdString(categoryId);

    //idsArrayForIndexPage, idOfPartialPage, idOfList]
    var idsArrayForHtml = makeArrayForHTMLAndContent(typeOfGift);

    appendFilterForGifts("#gifts .row", targetUserId, categoryId);
    appendResults(gifts, typeOfGift, idsArrayForHtml, userId, targetUserId);
}

//make array of constants from ids and classes
function makeArrayForHTMLAndContent(typeOfGift) {
    var testArray = [];

    testArray.push("#gifts .row");

    return testArray;
}

//append results that are caught from server
function appendResults(gifts, typeOfGift, idsArrayForHtml, userId, targetUserId) {
    var idOfList = idsArrayForHtml[0];

    //empty content of list before new results append
    //$(idOfList).empty();
    $(".recommended-gifts").hide();

    //if there is no recommended gifts show message that notifies user about that
    if ($.isEmptyObject(gifts)) {
        $(idOfList).append("<div class='col-sm-12 wow flipInY no-gifts recommended-gifts' data-wow-offset='10' data-wow-delay='0.2s'>Sorry, there are no recommended gifts for the category.</div>");
    }

    //skip first key of json array that is different for each category
    console.log(typeOfGift);
    console.log(gifts);
    switch (typeOfGift) {
        case 'music':
        case 'movies':
            gifts = gifts.get_productssearchsearch_response;
            break;
        case 'books':
            gifts = gifts.get_volumes_response;
            break;
        case 'events':
            gifts = gifts.get_search_response;
            break;
    }

    //for each gift render HTML
    $.each(gifts, function (i, element) {

        //skip element if it is equals to null
        if (element == null) {
            return true;
        }

        $.each(element, function (i, oneGift) {
            var imageOfGift, priceOfGift, giftTitle, linkForGift;

            switch (typeOfGift) {
                case 'books':
                    if (oneGift.volumeInfo.imageLinks != null) {
                        if (oneGift.volumeInfo.imageLinks.thumbnail === undefined) {
                            oneGift.volumeInfo.imageLinks.thumbnail = "img/no_image.png";
                        }
                        imageOfGift = oneGift.volumeInfo.imageLinks.thumbnail;
                    } else {
                        imageOfGift = "img/no_image.png";
                    }
                    if (oneGift.saleInfo.retailPrice === null) {
                        oneGift.saleInfo.retailPrice = "There is no price";
                    } else {
                        priceOfGift = oneGift.saleInfo.retailPrice.amount + "  " + EURO;
                    }
                    //currencyOfBook = oneGift.currencyCode;
                    giftTitle = oneGift.volumeInfo.title;
                    oneGift.volumeInfo.title = oneGift.volumeInfo.title.replace(/"/g, "");
                    oneGift.volumeInfo.title = oneGift.volumeInfo.title.replace(/'/g, "");
                    linkForGift = POPUP;
                    appendPartOfList(giftTitle, imageOfGift, priceOfGift, linkForGift, idOfList, i, oneGift, typeOfGift, userId, targetUserId);
                    break;
                case 'events':
                    $.each(oneGift, function (i, event) {
                        //sometimes this event has only one event and the other times it is consists of multiple events
                        if (event.length == undefined) {
                            giftTitle = event.title;
                            if (event.image === undefined) {
                                imageOfGift = "img/no_image.png";
                            } else {
                                imageOfGift = event.image.medium.url;
                            }
                            if (event.price === undefined) {
                                event.price = "There is no price";
                            }
                            priceOfGift = event.price;
                            linkForGift = POPUP;
                            appendPartOfList(giftTitle, imageOfGift, priceOfGift, linkForGift, idOfList, i, event, typeOfGift, userId, targetUserId);
                        } else {
                            $.each(event, function (i, subEvent) {
                                giftTitle = subEvent.title;
                                if (subEvent.image === undefined) {
                                    imageOfGift = "img/no_image.png";
                                } else {
                                    imageOfGift = subEvent.image.medium.url;
                                }
                                if (subEvent.price === undefined) {
                                    subEvent.price = "There is no price";
                                }
                                priceOfGift = event.price;
                                linkForGift = POPUP;
                                appendPartOfList(giftTitle, imageOfGift, priceOfGift, linkForGift, idOfList, i, subEvent, typeOfGift, userId, targetUserId);
                            });
                        }
                    });
                    break;
                default:
                    //music movie
                    if (oneGift.image === undefined) {
                        oneGift.image = "img/no_image.png";
                    }
                    imageOfGift = oneGift.image;
                    priceOfGift = oneGift.regularPrice + "  " + EURO; //special character for Euro
                    if (oneGift.name === undefined) {
                        oneGift.name = "No Name";
                    }
                    giftTitle = oneGift.name;
                    appendPartOfList(giftTitle, imageOfGift, priceOfGift, linkForGift, idOfList, i, oneGift, typeOfGift, userId, targetUserId);
            }

        });
    });
}

//append gift properties
function appendPartOfList(title, image, price, link, idOfList, i, element, typeOfGift, userId, targetUserId) {
    /*  $(idOfList).append("<li class='ui-li-static ui-body-inherit'><b>" + keyOfElement + ": " + "</b> " + valueOfElement + "</li>");*/

    //local variables
    var infoAboutGift = '';

    switch (typeOfGift) {
        case 'music':
            infoAboutGift = extraInfoForMusicAndMovies(element);
            break;
        case 'movies':
            infoAboutGift = extraInfoForMusicAndMovies(element);
            break;
        case 'books':
            var bookAuthors, bookCategory, languageOfBook, pagesOfBook, publisherOfBook, description, publishedDate;
            var imageOfGift, priceOfGift, giftTitle;

            if (!checkIfUndefined(element.volumeInfo.description)) {
                description = element.volumeInfo.description.replace(/"/g, '');
                element.volumeInfo.description = element.volumeInfo.description.replace(/"/g, '');
            } else {
                description = '';
            }
            element.description = description.replace(/"/g, '');
            bookAuthors = element.volumeInfo.authors;
            element.authors = bookAuthors;
            bookCategory = element.volumeInfo.category;
            languageOfBook = element.volumeInfo.language;
            element.language = languageOfBook;
            pagesOfBook = element.volumeInfo.pageCount;
            element.pageCount = pagesOfBook;
            publisherOfBook = element.volumeInfo.publisher;
            element.publisher = publisherOfBook;
            publishedDate = element.volumeInfo.publishedDate;

            if (!checkIfNull(element.volumeInfo.imageLinks)) {
                imageOfGift = element.volumeInfo.imageLinks.thumbnail;
                element.thumbnail = imageOfGift;
            } else {
                element.thumbnail = "img/no_image.png";
            }
            element.amount = element.saleInfo.retailPrice.amount;
            priceOfGift = element.amount + "  " + EURO;
            giftTitle = element.volumeInfo.title;
            element.title = giftTitle;
            element.currencyCode = element.saleInfo.currencyCode;

            if (bookCategory != null) {
                infoAboutGift += ' <p class="category-of-gift"><b>Category: </b>' + bookCategory + ' | <b>language: </b>' + languageOfBook + '</p><br>';
            }
            /*         infoAboutGift += ' | <label class="language-of-gift">' + languageOfBook + '</label> <br>';*/
            infoAboutGift += ' <p><b>Price: </b>' + priceOfGift + '</p> ';
            if (description != null) {
                infoAboutGift += '<p class="description-of-gift">' + description.replace(/"/g, '') + '</p>';
            }

            infoAboutGift +=  '<h6 id="book-authors">Authors </h6>';

            if (bookAuthors != null ) {
                infoAboutGift += '<label class="book-info" id="book-authors">' + bookAuthors + '</label>';
            } else {
                infoAboutGift += '<label class="book-info"></label>'
            }

            infoAboutGift += '<h6 id="book-publishers">Publisher</h6>';

            if (publisherOfBook != null){
                infoAboutGift +=  '<label class="book-info">' + publisherOfBook + '</label>';
            } else {
                infoAboutGift += '<label class="book-info"></label>'
            }

            if (publishedDate != null) {
                infoAboutGift += '<h6>Published</h6>';
                var date = publishedDate.split('-');
                infoAboutGift += '<label class="gift-published book-info">(' + date.reverse().join('.') + '.' + ')</label>';
            } else {
                infoAboutGift += '<label class="book-info"></label>'
            }

            infoAboutGift += '<h6 id="pages-of-book">Pages</h6>';

            if (pagesOfBook != null) {
                infoAboutGift += '<label class="book-info">' + pagesOfBook + '</label>';
            } else {
                infoAboutGift += '<label class="book-info"></label>';
            }

            if (description != null && description != "") {
                element.description = description.replace(/"/g, '');
            }
            break;
        case 'events':
            var cityName, countryName, startTime, description;
            var priceOfGift, giftTitle;

            cityName = element.city_name;
            countryName = element.country_name;
            startTime = element.start_time;
            description = element.description;

            giftTitle = element.title;
            priceOfGift = element.price;

            //format of sending git
            element.image = image;
            element.name = giftTitle;

            //parse time
            if (startTime != undefined) {
                var time = startTime.split(" ");
                var dateTime = time[0].split('-');
                time = dateTime.reverse().join('.') + '.' + "    " + time[1];
            }

            infoAboutGift += '<h2 id="event-started">Start time: </h2><h3>' + time + '</h3>' +
                ' <h6 id="event-city-name">City: </h6><label>' + cityName + '</label>' +
                '<h6 id="event-country-name">Country: </h6><label>' + countryName + '</label>';
            infoAboutGift += ' <p><b>Price: </b>' + priceOfGift + '</p> ';

            if (description != null) {
                description = description.replace(/"/g, '');
                description = description.replace(/(<([^>]+)>)/ig," ");
                infoAboutGift += '<p class="description-of-gift">' + description + '</p>';
            }

            if (description != null && description != '') {
                element.description = description.replace(/"/g, '');

                if (description.indexOf("<a href=") > 0) {
                    element.description = description.substring(0, description.indexOf("<a href="))
                }
            }

            break;
    }

    var stringObjectGift = makeStringFromGift(element, typeOfGift, userId, targetUserId);

    var stringGift = " <div class='col-sm-6 col-md-3 wow flipInY recommended-gifts' data-wow-offset='10' data-wow-delay='0.2s' id='" + i + "-gift'> " +
        "<div class='item-square'> " +
        "<div class='face'> " +
        "<img src='" + image + "' alt=''> " +
        "<div class='overlay'></div>" +
        "</div> " +
        "<div class='content'> " +
        "<div class='title'> " +
        "<h3>" + title + "</h3> " +
        "</div> " +
        "<div class='text'> " +
        "<p>" + infoAboutGift + "</p> " +
        "</div> " +
        "<div class='social-icons'> " +
        "<ul onclick='showWarningAboutBuyingGift(" + stringObjectGift + ");' style='cursor: pointer;'> " +
        "<li><a><i class='fa fa-gift'></i></a></li> " +
        "<li>Buy gift!</li>" +
        "</ul> " +
        "</div> " +
        "</div> " +
        "</div> " +
        "</div>";

    $(idOfList).append(stringGift);
}

//append extra info for movies and music beacause they are the same
function extraInfoForMusicAndMovies(element) {
    var imageOfGift, priceOfGift, giftTitle;

    imageOfGift = element.image;
    priceOfGift = element.regularPrice + "  " + EURO;
    giftTitle = element.name;

    var infoForAppend = '';
    infoForAppend += ' <p class="price-of-gift"><b>Price: </b>' + priceOfGift + '</p> ';

    return infoForAppend;
}

//convert gift object to string
function makeStringFromGift(gift, typeOfGift, userId, targetUserId) {
    //add category id
    var categoryId;
    categoryId = getCategoryIdFromName(typeOfGift);

    var userFacebookId = window.localStorage.getItem('facebookId');

    gift.categoryId = categoryId;
    gift.recipient = targetUserId;
    gift.sender = userFacebookId;
    gift.status = "pending";

    gift.sendTime = new Date().getTime().toString();

    //rename shortDescription in description
    if ('shortDescription' in gift) {
        gift.description = gift.shortDescription.replace(/"/g, '');
        delete gift.shortDescription;
    }

    /* gift.description = gift.description.replace("\"", ' ');
     console.log(gift.description);*/
    if (gift.description != null) {
        if (gift.description.indexOf("\"") > 0) {
            gift.description = null;
        }
        gift.description = gift.description.replace(/"/g, "");
        gift.description = gift.description.replace(/'/g, "");
    }

    if (!checkIfUndefined(gift.volumeInfo)) {
        if (gift.volumeInfo.description != null) {
            if (gift.volumeInfo.description.indexOf("\"") > 0) {
                gift.volumeInfo.description = null;
            }
            gift.volumeInfo.description = gift.volumeInfo.description.replace(/"/g, "");
            gift.volumeInfo.description = gift.volumeInfo.description.replace(/'/g, "");
        }
    }

    var giftString = JSON.stringify(gift);

    return giftString;
    /*info += "<button type='submit' value='Submit' onclick='postGiftProposal(" + giftString + ")' class='ui-btn submit-button'>Buy gift!</button>";
     return info;*/
}

function showWarningAboutBuyingGift(stringObjectGift) {
    setShowModal("Are you sure you want to buy a gift?", "Please be absolutely certain that you want to give this gift", "No, thanks", "Yes, I'm sure");

    $(".modal-footer .btn-success").attr("onclick", 'buyGift(' + JSON.stringify(stringObjectGift) + ')');
}

function buyGift(stringObjectGift) {
    postGiftProposal(stringObjectGift);
}

function checkIfUndefined(element) {
    if (element === undefined) {
        return true;
    } else {
        return false;
    }
}

function checkIfNull(element) {
    if (element === null) {
        return true;
    } else {
        return false;
    }
}