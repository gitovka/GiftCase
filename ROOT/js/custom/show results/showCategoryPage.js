function goToCategoryPage(targetUserId) {
    //show category page
    $("#categories").show();

    //redirect to category page
    goToSection("categories");

    $(".service-box").on("click", function () {
        event.preventDefault();

        //remove all warning on page
        removeWarnings();

        var categoryId = $(this).attr("id");
        //refresh count value on 10
        COUNT = "10";

        //before going on show gift page it needs to be asked user parameters of gift
        //parameters for music, books, movies are minPrice, maxPrice, count, startIndex
        //parameters for events are startIndex, count, location, radius
        var locationOfUser = null;
        var locationRadius = null;
        var count = null;
        var startIndex = null;
        var minPrice = null;
        var maxPrice = null;

        if (categoryId === "4") {
            makeUrlToGetGifts(targetUserId, categoryId, locationOfUser, locationRadius, count, startIndex);
        } else {
            makeUrlToGetGifts(targetUserId, categoryId, minPrice, maxPrice, count, startIndex);
        }
    });
}