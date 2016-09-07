function chooseCategoryForTargetUser(targetUserId) {

    var userId = window.localStorage.getItem('userId');
    var loadingSelector = $("#loading-categories");

    //get categories from backend side and append it on DOM
    $.ajax({
        type: "GET",
        dataType: 'json',
        url: BASE_URL + "gifts/categories?targetFacebookId=" + targetUserId,
        beforeSend: function () {
            loadingSelector.show();
        },
        success: function (jsonObj, textStatus, xhr) {
               console.log(jsonObj);

            //hide loader
            loadingSelector.fadeOut("slow");

            //menage exceptions
            var resultOfException = checkIfExceptionExists(jsonObj);
            if (resultOfException) {
                menageExceptions(resultOfException, 'chooseCategoryForTargetUser(' + targetUserId + ')');
            }

            var categories = jsonObj;
            var categoriesListSelector = $("#categories .row");

            categoriesListSelector.empty();

            //initial explanation of the categories if the one is needed
            categoriesListSelector.append('<div class="categories-explanation wow fadeIn" data-wow-delay=".2s"> ' +
                '<p>If user Internet consumption > average consumption and user Internet connection duration < average ' +
                'duration, <span class="category-color">music</span> and <span class="category-color">movies</span> ' +
                'categories are shown. </p> ' +
                '<p>If user Internet consumption < average consumption and user Internet connection duration > average' +
                'duration, <span class="category-color">books</span> and <span class="category-color">events</span> ' +
                'categories are shown. </p> ' +
                '<p>Otherwise, <span class="category-color">all</span> categories are shown. </p>' +
                '</div>');

            //for each content of categories array append list on category-
            $.each(categories, function (key, category) {

                //local variables
                var categoryIcon;

                //set categoryIcon for every type of gift
                switch (category) {
                    case 'Music':
                        categoryIcon = 'music';
                        break;
                    case 'Movies':
                        categoryIcon = 'video-camera';
                        break;
                    case 'Books':
                        categoryIcon = 'book';
                        break;
                    case 'Events':
                        categoryIcon = 'map-marker';
                        break;
                }

                categoriesListSelector.append('<div class="col-sm-6 col-md-3 wow fadeInUp"> ' +
                    '<div class="service-box" id="' + key + '" style="cursor: pointer;"> ' +
                    '<div class="content"> ' +
                    '<div class="icon-wrapper"> ' +
                    '<i class="fa fa-' + categoryIcon + '"></i> ' +
                    '</div> ' +
                    '<h2>' + category + '</h2> ' +
                    '<p>Give this to your friend</p> ' +
                    '</div> ' +
                    '</div> ' +
                    '</div>'
                );
            });
			
			//go to categories section
            $("#simulate-categories").click();

            //bind click event on categories
            goToCategoryPage(targetUserId);
        },
        error: function (xhr, textStatus, errorThrown) {
            console.log(xhr.status);
        }
    });
    //ajax completed
}