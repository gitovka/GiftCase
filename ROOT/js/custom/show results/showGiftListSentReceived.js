function makeListSentReceived(gift, inOSent, recSendName) {
    var category, image, name, price, icon;
    var categoryName = getCategoryNameFromId(gift.categoryId);
    var resultString = "";

    switch (categoryName) {
        case 'music':
        case 'movies':
            category = categoryName;
            if (!gift.hasOwnProperty('image')) {
                gift.image = "img/no_image.png";
            }
            image = gift.image;
            if (!gift.hasOwnProperty('name')) {
                gift.name = "No Name";
            }
            name = gift.name;
            price = gift.regularPrice + "  " + EURO;
            break;
        case 'books':
            category = categoryName.slice(0, -1);
            if (!gift.hasOwnProperty('thumbnail')) {
                gift.thumbnail = "img/no_image.png";
            }
            image = gift.thumbnail;
            if (!gift.hasOwnProperty('title')) {
                gift.title = "No Name";
            }
            name = gift.title;
            price = gift.amount + "  " + EURO;
            break;
        case 'events':
            category = categoryName.slice(0, -1);
            if (!gift.hasOwnProperty('image')) {
                gift.image = "img/no_image.png";
            }
            image = gift.image;
            if (!gift.hasOwnProperty('title')) {
                name = "No Name";
            } else {
                name = gift.title;
            }
            price = gift.price;
            break;
    }

    //slice movies to become movie
    if (categoryName === "movies") {
        category = categoryName.slice(0, -1);
    }

    var nameOfSenderOrRecipient;
    //if showing inbox than from, if showing sentbox than to
    if (inOSent == 'sent') {
        nameOfSenderOrRecipient = "<span>to: </span>" + recSendName;
    } else {
        nameOfSenderOrRecipient = "<span>from: </span>" + recSendName;
    }

    resultString += "<div class='item-square'> " +
        "<div class='face'> " +
        "<img src='" + image + "' alt=''> " +
        "<div class='overlay'></div>" +
        "</div> " +
        "<div class='content'> " +
        "<div class='title'> " +
        "<h3>" + name + "</h3> " +
        "<h4>" + nameOfSenderOrRecipient + "</h4> " +
        "</div> " +
        "<div class='text'> " +
        "<p class='price-of-gift'><b>Price: </b>" + price + "</p> " +
        "</div> ";

    return resultString;
}