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


import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import eu.fp7.scase.giftcaseapi.EventGift.JavaEventGiftModel;
import eu.fp7.scase.giftcaseapi.BookGift.JavaBookGiftModel;
import eu.fp7.scase.giftcaseapi.MusicMovieGift.JavaMusicMovieGiftModel;
import eu.fp7.scase.giftcaseapi.Users.JavaUsersModel;

import java.sql.*;

/* This class follows the singleton pattern in order to build once and provide a unique hibernate session instance*/

public class HibernateUtil{

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        try {
        //Since Hibernate cannot create PostgreSQL databases, utilize jdbc code to check if the database exist
        createDBIfDoesNotExist();
        /* Create the unique hibernate session. All resource models should be added here.*/
            return new AnnotationConfiguration().configure()
					.addAnnotatedClass(JavaEventGiftModel.class)
					.addAnnotatedClass(JavaBookGiftModel.class)
					.addAnnotatedClass(JavaMusicMovieGiftModel.class)
					.addAnnotatedClass(JavaUsersModel.class)
                    .buildSessionFactory();
        }
        catch (Throwable ex){
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    private static void createDBIfDoesNotExist(){
        String strJDBCDRIVER = "org.postgresql.Driver";
        String strDatabaseURL = "jdbc:postgresql://localhost:5432/postgres";
        String strUserName  = "postgres";
        String strPassword  = "fp7s-case";
        
        Connection oConnection = null;
        Statement oStatement = null;
        
        try{
            //register the jdbc driver
            Class.forName(strJDBCDRIVER);
            
            //connect to database
            oConnection = DriverManager.getConnection(strDatabaseURL, strUserName, strPassword);
            
            //initializae the database creating statement
            oStatement = oConnection.createStatement();
            String strCheckIfDatabaseExistsQuery = String.format("select datname FROM pg_database WHERE datname = '%s';", "giftcaseapi");
            
            //execute query
            ResultSet oResultSet = oStatement.executeQuery(strCheckIfDatabaseExistsQuery);
            if(oResultSet.next() == false){
                System.out.println("The database does not exist!");
                String strCreateDatabaseQuery = String.format("create database %s;" , "giftcaseapi");
                oResultSet = oStatement.executeQuery(strCreateDatabaseQuery);
            }
            else{
                System.out.println("The database already exists!");
            }
        }
        catch(SQLException SQLe){
            SQLe.printStackTrace();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            try{
                if(oStatement != null){
                    oConnection.close();
                }
            }
            catch(SQLException SQLe){
                SQLe.printStackTrace();
            }
        }
    }
}
