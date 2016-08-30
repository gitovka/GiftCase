/*
 * ARISTOTLE UNIVERSITY OF THESSALONIKI
 * Copyright (C) 2015
 * Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering
 * Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 *
 * Project             : GiftCaseApi
 * WorkFile            : 
 * Compiler            : 
 * File Description    : 
 * Document Description: 
* Related Documents	   : 
* Note				   : 
* Programmer		   : RESTful MDE Engine created by Christoforos Zolotas
* Contact			   : christopherzolotas@issel.ee.auth.gr
*/


package eu.fp7.scase.giftcaseapi.utilities;


import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import eu.fp7.scase.giftcaseapi.EventGift.JavaEventGiftController;
import eu.fp7.scase.giftcaseapi.BookGift.JavaBookGiftController;
import eu.fp7.scase.giftcaseapi.MusicMovieGift.JavaMusicMovieGiftController;
import eu.fp7.scase.giftcaseapi.Users.JavaUsersController;
import eu.fp7.scase.giftcaseapi.EventGift.JavaEventGiftControllerManager;
import eu.fp7.scase.giftcaseapi.BookGift.JavaBookGiftControllerManager;
import eu.fp7.scase.giftcaseapi.MusicMovieGift.JavaMusicMovieGiftControllerManager;
import eu.fp7.scase.giftcaseapi.Users.JavaUsersControllerManager;
import eu.fp7.scase.giftcaseapi.getMoviesSocial.JavaAlgogetMoviesSocialController;
import eu.fp7.scase.giftcaseapi.getMusicSocial.JavaAlgogetMusicSocialController;
import eu.fp7.scase.giftcaseapi.getFacebookFriends.JavaAlgogetFacebookFriendsController;
import eu.fp7.scase.giftcaseapi.getEventsSocial.JavaAlgogetEventsSocialController;
import eu.fp7.scase.giftcaseapi.giftStatus.JavaAlgogiftStatusController;
import eu.fp7.scase.giftcaseapi.receivedGift.JavaAlgoreceivedGiftController;
import eu.fp7.scase.giftcaseapi.sentGift.JavaAlgosentGiftController;
import eu.fp7.scase.giftcaseapi.getBooksSocial.JavaAlgogetBooksSocialController;
import eu.fp7.scase.giftcaseapi.getAboutUser.JavaAlgogetAboutUserController;
import eu.fp7.scase.giftcaseapi.facebookLogin.JavaAlgofacebookCallbackController;
import eu.fp7.scase.giftcaseapi.facebookLogin.JavaAlgofacebookLoginController;
import eu.fp7.scase.giftcaseapi.facebookLogin.JavaAlgofacebookLogoutController;
import eu.fp7.scase.giftcaseapi.getCategories.JavaAlgogetCategoriesController;

/* This class is responsible to publish any resource controllers that can handle incoming HTTP requests
to the web service container (Jetty etc)*/

@ApplicationPath("/api/")
public class JAXRSPublisher extends Application{

    public JAXRSPublisher(){}

    /* This function returns to the container (Jetty, Tomcat etc) the classes that expose any web API*/
    @Override
    public Set<Class<?>> getClasses(){
        HashSet<Class<?>> SetOfClasses = new HashSet<Class<?>>();
		SetOfClasses.add(JavaEventGiftController.class);
		SetOfClasses.add(JavaBookGiftController.class);
		SetOfClasses.add(JavaMusicMovieGiftController.class);
		SetOfClasses.add(JavaUsersController.class);
		SetOfClasses.add(JavaEventGiftControllerManager.class);
		SetOfClasses.add(JavaBookGiftControllerManager.class);
		SetOfClasses.add(JavaMusicMovieGiftControllerManager.class);
		SetOfClasses.add(JavaUsersControllerManager.class);
		SetOfClasses.add(JavaAlgogetMoviesSocialController.class);
		SetOfClasses.add(JavaAlgogetMusicSocialController.class);
		SetOfClasses.add(JavaAlgogetFacebookFriendsController.class);
		SetOfClasses.add(JavaAlgogetEventsSocialController.class);
		SetOfClasses.add(JavaAlgogiftStatusController.class);
		SetOfClasses.add(JavaAlgoreceivedGiftController.class);
		SetOfClasses.add(JavaAlgosentGiftController.class);
		SetOfClasses.add(JavaAlgogetBooksSocialController.class);
		SetOfClasses.add(JavaAlgogetAboutUserController.class);
		SetOfClasses.add(JavaAlgofacebookLoginController.class);
		SetOfClasses.add(JavaAlgofacebookLogoutController.class);
		SetOfClasses.add(JavaAlgogetCategoriesController.class);
		SetOfClasses.add(JavaAlgofacebookCallbackController.class);
		

        return SetOfClasses;
    }

    @Override
    public Set<Object> getSingletons(){
        return new HashSet<Object>();
    }
}
