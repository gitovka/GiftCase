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




import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;

import eu.fp7.scase.giftcaseapi.EventGift.JavaEventGiftModel;
import eu.fp7.scase.giftcaseapi.BookGift.JavaBookGiftModel;
import eu.fp7.scase.giftcaseapi.MusicMovieGift.JavaMusicMovieGiftModel;
import eu.fp7.scase.giftcaseapi.Users.JavaUsersModel;
import eu.fp7.scase.giftcaseapi.facebookLogin.JavaAlgofacebookCallbackController;

/* HibernateController class is responsible to handle the low level activity between Hibernate and the service database.
 You may not alter existing functions, or the service may not function properly.
 Should you need more functions these could be added at the end of this file.
 You may add any exception handling to existing and/or new functions of this file.*/

public class HibernateController{

    private static HibernateController oHibernateController = new HibernateController();

    /* Since the class follows the singleton design pattern its constructor is kept private. The unique instance of it is accessed through its public API "getHibernateControllerHandle()".*/
    public HibernateController(){
	}

    /* Since this class follows the singleton design pattern, this function offers to the rest of the system a handle to its unique instance.*/
    public static HibernateController getHibernateControllerHandle(){
        return oHibernateController;
    }


    /* This function handles the low level JPA activities so as to add a new EventGift resource to the service database.*/
    public JavaEventGiftModel postEventGift(JavaEventGiftModel oJavaEventGiftModel){

    	/* Create a new hibernate session and begin the transaction*/
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Transaction hibernateTransaction = hibernateSession.beginTransaction();

        /* Insert the new EventGift to database*/
        int EventGiftId = (Integer) hibernateSession.save(oJavaEventGiftModel);

        /* Commit and terminate the session*/
        hibernateTransaction.commit();
        hibernateSession.close();

        /* Return the JavaEventGiftModel with updated EventGiftId*/
        oJavaEventGiftModel.setEventGiftId(EventGiftId);
        return oJavaEventGiftModel;
    }
	
    /* This function handles the low level hibernate activities so as to retrieve an existing EventGift resource from the service database.*/
    public JavaEventGiftModel getEventGift(JavaEventGiftModel oJavaEventGiftModel){

    	/* Create a new hibernate session and begin the transaction*/
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Transaction hibernateTransaction = hibernateSession.beginTransaction();

        /* Retrieve the existing EventGift from the database*/
        oJavaEventGiftModel = (JavaEventGiftModel) hibernateSession.get(JavaEventGiftModel.class, oJavaEventGiftModel.getEventGiftId());

        /* Commit and terminate the session*/
        hibernateTransaction.commit();
        hibernateSession.close();
        return oJavaEventGiftModel;
    }

    /* This function handles the low level hibernate activities so as to retrieve all the EventGift resources from the service database.*/

    public Set<JavaEventGiftModel> getEventGiftList(Set<JavaEventGiftModel> SetOfEventGiftList){

        /* Create a new hibernate session and begin the transaction*/
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Transaction hibernateTransaction = hibernateSession.beginTransaction();

        /* Retrieve the list of EventGift resources that are needed.*/
        String strHibernateQuery = "FROM JavaEventGiftModel";
        Query hibernateQuery = hibernateSession.createQuery(strHibernateQuery);
        SetOfEventGiftList = new HashSet(hibernateQuery.list());

        /* Commit and terminate the session*/
        hibernateTransaction.commit();
        hibernateSession.close();
        return SetOfEventGiftList;
    }
    /* This function handles the low level JPA activities so as to add a new BookGift resource to the service database.*/
    public JavaBookGiftModel postBookGift(JavaBookGiftModel oJavaBookGiftModel){

    	/* Create a new hibernate session and begin the transaction*/
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Transaction hibernateTransaction = hibernateSession.beginTransaction();

        /* Insert the new BookGift to database*/
        int BookGiftId = (Integer) hibernateSession.save(oJavaBookGiftModel);

        /* Commit and terminate the session*/
        hibernateTransaction.commit();
        hibernateSession.close();

        /* Return the JavaBookGiftModel with updated BookGiftId*/
        oJavaBookGiftModel.setBookGiftId(BookGiftId);
        return oJavaBookGiftModel;
    }
	
    /* This function handles the low level hibernate activities so as to retrieve an existing BookGift resource from the service database.*/
    public JavaBookGiftModel getBookGift(JavaBookGiftModel oJavaBookGiftModel){

    	/* Create a new hibernate session and begin the transaction*/
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Transaction hibernateTransaction = hibernateSession.beginTransaction();

        /* Retrieve the existing BookGift from the database*/
        oJavaBookGiftModel = (JavaBookGiftModel) hibernateSession.get(JavaBookGiftModel.class, oJavaBookGiftModel.getBookGiftId());

        /* Commit and terminate the session*/
        hibernateTransaction.commit();
        hibernateSession.close();
        return oJavaBookGiftModel;
    }

    /* This function handles the low level hibernate activities so as to retrieve all the BookGift resources from the service database.*/

    public Set<JavaBookGiftModel> getBookGiftList(Set<JavaBookGiftModel> SetOfBookGiftList){

        /* Create a new hibernate session and begin the transaction*/
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Transaction hibernateTransaction = hibernateSession.beginTransaction();

        /* Retrieve the list of BookGift resources that are needed.*/
        String strHibernateQuery = "FROM JavaBookGiftModel";
        Query hibernateQuery = hibernateSession.createQuery(strHibernateQuery);
        SetOfBookGiftList = new HashSet(hibernateQuery.list());

        /* Commit and terminate the session*/
        hibernateTransaction.commit();
        hibernateSession.close();
        return SetOfBookGiftList;
    }
    /* This function handles the low level JPA activities so as to add a new MusicMovieGift resource to the service database.*/
    public JavaMusicMovieGiftModel postMusicMovieGift(JavaMusicMovieGiftModel oJavaMusicMovieGiftModel){

    	/* Create a new hibernate session and begin the transaction*/
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Transaction hibernateTransaction = hibernateSession.beginTransaction();

        /* Insert the new MusicMovieGift to database*/
        int MusicMovieGiftId = (Integer) hibernateSession.save(oJavaMusicMovieGiftModel);

        /* Commit and terminate the session*/
        hibernateTransaction.commit();
        hibernateSession.close();

        /* Return the JavaMusicMovieGiftModel with updated MusicMovieGiftId*/
        oJavaMusicMovieGiftModel.setMusicMovieGiftId(MusicMovieGiftId);
        return oJavaMusicMovieGiftModel;
    }
	
    /* This function handles the low level hibernate activities so as to retrieve an existing MusicMovieGift resource from the service database.*/
    public JavaMusicMovieGiftModel getMusicMovieGift(JavaMusicMovieGiftModel oJavaMusicMovieGiftModel){

    	/* Create a new hibernate session and begin the transaction*/
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Transaction hibernateTransaction = hibernateSession.beginTransaction();

        /* Retrieve the existing MusicMovieGift from the database*/
        oJavaMusicMovieGiftModel = (JavaMusicMovieGiftModel) hibernateSession.get(JavaMusicMovieGiftModel.class, oJavaMusicMovieGiftModel.getMusicMovieGiftId());

        /* Commit and terminate the session*/
        hibernateTransaction.commit();
        hibernateSession.close();
        return oJavaMusicMovieGiftModel;
    }

    /* This function handles the low level hibernate activities so as to retrieve all the MusicMovieGift resources from the service database.*/

    public Set<JavaMusicMovieGiftModel> getMusicMovieGiftList(Set<JavaMusicMovieGiftModel> SetOfMusicMovieGiftList){

        /* Create a new hibernate session and begin the transaction*/
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Transaction hibernateTransaction = hibernateSession.beginTransaction();

        /* Retrieve the list of MusicMovieGift resources that are needed.*/
        String strHibernateQuery = "FROM JavaMusicMovieGiftModel";
        Query hibernateQuery = hibernateSession.createQuery(strHibernateQuery);
        SetOfMusicMovieGiftList = new HashSet(hibernateQuery.list());

        /* Commit and terminate the session*/
        hibernateTransaction.commit();
        hibernateSession.close();
        return SetOfMusicMovieGiftList;
    }
    /* This function handles the low level JPA activities so as to add a new Users resource to the service database.*/
    public JavaUsersModel postUsers(JavaUsersModel oJavaUsersModel){

    	/* Create a new hibernate session and begin the transaction*/
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Transaction hibernateTransaction = hibernateSession.beginTransaction();

        /* Insert the new Users to database*/
        int UsersId = (Integer) hibernateSession.save(oJavaUsersModel);

        /* Commit and terminate the session*/
        hibernateTransaction.commit();
        hibernateSession.close();

        /* Return the JavaUsersModel with updated UsersId*/
        oJavaUsersModel.setUsersId(UsersId);
        return oJavaUsersModel;
    }
	
    /* This function handles the low level hibernate activities so as to update an existing Users resource of the service database.*/
    public JavaUsersModel putUsers(JavaUsersModel oJavaUsersModel){

    	/* Create a new hibernate session and begin the transaction*/
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Transaction hibernateTransaction = hibernateSession.beginTransaction();

        /* Update the existing Users of the database*/
        hibernateSession.update(oJavaUsersModel);

        /* Commit and terminate the session*/
        hibernateTransaction.commit();
        hibernateSession.close();
        return oJavaUsersModel;
    }

    /* This function handles the low level hibernate activities so as to retrieve an existing Users resource from the service database.*/
    public JavaUsersModel getUsers(JavaUsersModel oJavaUsersModel){

    	/* Create a new hibernate session and begin the transaction*/
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Transaction hibernateTransaction = hibernateSession.beginTransaction();

        /* Retrieve the existing Users from the database*/
        oJavaUsersModel = (JavaUsersModel) hibernateSession.get(JavaUsersModel.class, oJavaUsersModel.getUsersId());

        /* Commit and terminate the session*/
        hibernateTransaction.commit();
        hibernateSession.close();
        return oJavaUsersModel;
    }

    /* This function handles the low level hibernate activities so as to retrieve all the Users resources from the service database.*/

    public Set<JavaUsersModel> getUsersList(Set<JavaUsersModel> SetOfUsersList){

        /* Create a new hibernate session and begin the transaction*/
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Transaction hibernateTransaction = hibernateSession.beginTransaction();

        /* Retrieve the list of Users resources that are needed.*/
        String strHibernateQuery = "FROM JavaUsersModel";
        Query hibernateQuery = hibernateSession.createQuery(strHibernateQuery);
        SetOfUsersList = new HashSet(hibernateQuery.list());

        /* Commit and terminate the session*/
        hibernateTransaction.commit();
        hibernateSession.close();
        return SetOfUsersList;
    }

	// added function - checking if user exists
	public List<JavaUsersModel> getUserByFacebookId(String facebookId) {

		/* Create a new hibernate session and begin the transaction */
		Session hibernateSession = HibernateUtil.getSessionFactory()
				.openSession();
		Transaction hibernateTransaction = hibernateSession.beginTransaction();

		/* Retrieve the list of Users resources that are needed. */
		Query query = hibernateSession
				.createQuery("FROM JavaUsersModel where facebookId=:facebookId");
		query.setParameter("facebookId", facebookId);

		List<JavaUsersModel> user = (List<JavaUsersModel>) query.list();

		/* Commit and terminate the session */
		hibernateTransaction.commit();
		hibernateSession.close();

		return user;
	}

	public List<JavaUsersModel> getUserByUsersId(int usersId) {

		/* Create a new hibernate session and begin the transaction */
		Session hibernateSession = HibernateUtil.getSessionFactory()
				.openSession();
		Transaction hibernateTransaction = hibernateSession.beginTransaction();

		/* Retrieve the list of Users resources that are needed. */
		Query query = hibernateSession
				.createQuery("FROM JavaUsersModel where usersId=:id");
		query.setParameter("id", usersId);

		List<JavaUsersModel> user = (List<JavaUsersModel>) query.list();

		/* Commit and terminate the session */
		hibernateTransaction.commit();
		hibernateSession.close();

		return user;
	}

	// added function - checking if user exists
	public boolean updateUser(String facebookId, String accesstoken,
			boolean isUserLoggedIn, String location, String hometown,
			String email, String firstname, String lastname,
			String profilepicture) {

		/* Create a new hibernate session and begin the transaction */
		Session hibernateSession = HibernateUtil.getSessionFactory()
				.openSession();
		Transaction hibernateTransaction = hibernateSession.beginTransaction();

		/* Retrieve the list of Users resources that are needed. */
		Query query = hibernateSession
				.createQuery("update JavaUsersModel set accesstoken=:accesstoken,  isuserloggedin=:isuserloggedin, location=:location, hometown=:hometown, "
						+ "email=:email, firstname=:firstname, lastname=:lastname, profilepicture=:profilepicture WHERE facebookid=:facebookid ");
		query.setParameter("accesstoken", accesstoken);
		query.setParameter("isuserloggedin", isUserLoggedIn);
		query.setParameter("location", location);
		query.setParameter("hometown", hometown);
		query.setParameter("email", email);
		query.setParameter("firstname", firstname);
		query.setParameter("lastname", lastname);
		query.setParameter("profilepicture", profilepicture);
		query.setParameter("facebookid", facebookId);

		query.executeUpdate();

		/* Commit and terminate the session */
		hibernateTransaction.commit();
		hibernateSession.close();

		return true;
	}

	public boolean updateUser(int usersId, boolean isUserLoggedIn) {

		/* Create a new hibernate session and begin the transaction */
		Session hibernateSession = HibernateUtil.getSessionFactory()
				.openSession();
		Transaction hibernateTransaction = hibernateSession.beginTransaction();

		/* Retrieve the list of Users resources that are needed. */
		Query query = hibernateSession
				.createQuery("update JavaUsersModel set isuserloggedin=:isuserloggedin WHERE usersid=:usersid");

		query.setParameter("isuserloggedin", isUserLoggedIn);
		query.setParameter("usersid", usersId);

		query.executeUpdate();

		/* Commit and terminate the session */
		hibernateTransaction.commit();
		hibernateSession.close();

		return true;
	}

	public boolean updateMusicMovieGift(String sendTime, String status) {

		/* Create a new hibernate session and begin the transaction */
		Session hibernateSession = HibernateUtil.getSessionFactory()
				.openSession();
		Transaction hibernateTransaction = hibernateSession.beginTransaction();

		/* Retrieve the list of Users resources that are needed. */
		Query query = hibernateSession
				.createQuery("update JavaMusicMovieGiftModel set status=:status WHERE sendTime=:sendTime");

		query.setParameter("status", status);
		query.setParameter("sendTime", sendTime);

		query.executeUpdate();

		/* Commit and terminate the session */
		hibernateTransaction.commit();
		hibernateSession.close();

		return true;
	}

	public boolean updateBookGift(String sendTime, String status) {

		/* Create a new hibernate session and begin the transaction */
		Session hibernateSession = HibernateUtil.getSessionFactory()
				.openSession();
		Transaction hibernateTransaction = hibernateSession.beginTransaction();

		/* Retrieve the list of Users resources that are needed. */
		Query query = hibernateSession
				.createQuery("update JavaBookGiftModel set status=:status WHERE sendTime=:sendTime");

		query.setParameter("status", status);
		query.setParameter("sendTime", sendTime);

		query.executeUpdate();

		/* Commit and terminate the session */
		hibernateTransaction.commit();
		hibernateSession.close();

		return true;
	}

	public boolean updateEventGift(String sendTime, String status) {

		/* Create a new hibernate session and begin the transaction */
		Session hibernateSession = HibernateUtil.getSessionFactory()
				.openSession();
		Transaction hibernateTransaction = hibernateSession.beginTransaction();

		/* Retrieve the list of Users resources that are needed. */
		Query query = hibernateSession
				.createQuery("update JavaEventGiftModel set status=:status WHERE sendTime=:sendTime");

		query.setParameter("status", status);
		query.setParameter("sendTime", sendTime);

		query.executeUpdate();

		/* Commit and terminate the session */
		hibernateTransaction.commit();
		hibernateSession.close();

		return true;
	}

	@SuppressWarnings("unchecked")
	public List<JavaMusicMovieGiftModel> getReceivedMusicMovieGift(
			int usersId) {

		String facebookId = getUserByUsersId(usersId).get(0).getfacebookId();

		/* Create a new hibernate session and begin the transaction */
		Session hibernateSession = HibernateUtil.getSessionFactory()
				.openSession();
		Transaction hibernateTransaction = hibernateSession.beginTransaction();

		/* Retrieve the list of Users resources that are needed. */
		Query query = hibernateSession.createQuery("FROM JavaMusicMovieGiftModel where recipient=:id");
		query.setParameter("id", facebookId);

		List<JavaMusicMovieGiftModel> gifts = (List<JavaMusicMovieGiftModel>) query.list();

		/* Commit and terminate the session */
		hibernateTransaction.commit();
		hibernateSession.close();

		return gifts;
	}

	public List<JavaEventGiftModel> getReceivedEventGift(int usersId) {

		String facebookId = getUserByUsersId(usersId).get(0).getfacebookId();

		/* Create a new hibernate session and begin the transaction */
		Session hibernateSession = HibernateUtil.getSessionFactory()
				.openSession();
		Transaction hibernateTransaction = hibernateSession.beginTransaction();

		/* Retrieve the list of Users resources that are needed. */
		Query query = hibernateSession
				.createQuery("FROM JavaEventGiftModel where recipient=:id");
		query.setParameter("id", facebookId);

		@SuppressWarnings("unchecked")
		List<JavaEventGiftModel> gifts = (List<JavaEventGiftModel>) query
				.list();

		/* Commit and terminate the session */
		hibernateTransaction.commit();
		hibernateSession.close();

		return gifts;
	}

	public List<JavaBookGiftModel> getReceivedBookGift(int usersId) {

		String facebookId = getUserByUsersId(usersId).get(0).getfacebookId();

		/* Create a new hibernate session and begin the transaction */
		Session hibernateSession = HibernateUtil.getSessionFactory()
				.openSession();
		Transaction hibernateTransaction = hibernateSession.beginTransaction();

		/* Retrieve the list of Users resources that are needed. */
		Query query = hibernateSession
				.createQuery("FROM JavaBookGiftModel where recipient=:id");
		query.setParameter("id", facebookId);

		@SuppressWarnings("unchecked")
		List<JavaBookGiftModel> gifts = (List<JavaBookGiftModel>) query.list();

		/* Commit and terminate the session */
		hibernateTransaction.commit();
		hibernateSession.close();

		return gifts;
	}

	@SuppressWarnings("unchecked")
	public List<JavaMusicMovieGiftModel> getSentMusicMovieGift(int usersId) {

		String facebookId = getUserByUsersId(usersId).get(0).getfacebookId();

		/* Create a new hibernate session and begin the transaction */
		Session hibernateSession = HibernateUtil.getSessionFactory()
				.openSession();
		Transaction hibernateTransaction = hibernateSession.beginTransaction();

		/* Retrieve the list of Users resources that are needed. */
		Query query = hibernateSession
				.createQuery("FROM JavaMusicMovieGiftModel where sender=:id");
		query.setParameter("id", facebookId);

		List<JavaMusicMovieGiftModel> gifts = (List<JavaMusicMovieGiftModel>) query.list();


		/* Commit and terminate the session */
		hibernateTransaction.commit();
		hibernateSession.close();

		return gifts;
	}

	final static Logger logger = Logger.getLogger(HibernateController.class);
	
	@SuppressWarnings("unchecked")
	public List<JavaEventGiftModel> getSentEventGift(int usersId) {

		String facebookId = getUserByUsersId(usersId).get(0).getfacebookId();

		/* Create a new hibernate session and begin the transaction */
		Session hibernateSession = HibernateUtil.getSessionFactory()
				.openSession();
		Transaction hibernateTransaction = hibernateSession.beginTransaction();

		/* Retrieve the list of Users resources that are needed. */
		Query query = hibernateSession
				.createQuery("FROM JavaEventGiftModel where sender=:id");
		query.setParameter("id", facebookId);

		List<JavaEventGiftModel> gifts = (List<JavaEventGiftModel>) query.list();

		/* Commit and terminate the session */
		hibernateTransaction.commit();
		hibernateSession.close();

		return gifts;
	}

	@SuppressWarnings("unchecked")
	public List<JavaBookGiftModel> getSentBookGift(int usersId) {

		String facebookId = getUserByUsersId(usersId).get(0).getfacebookId();

		/* Create a new hibernate session and begin the transaction */
		Session hibernateSession = HibernateUtil.getSessionFactory()
				.openSession();
		Transaction hibernateTransaction = hibernateSession.beginTransaction();

		/* Retrieve the list of Users resources that are needed. */
		Query query = hibernateSession
				.createQuery("FROM JavaBookGiftModel where sender=:id");
		query.setParameter("id", facebookId);

		List<JavaBookGiftModel> gifts = (List<JavaBookGiftModel>) query.list();

		/* Commit and terminate the session */
		hibernateTransaction.commit();
		hibernateSession.close();

		return gifts;
	}

}
