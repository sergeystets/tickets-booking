tickets-booking
===============

                                      IN GENERAL
	  Tickets-booking is a simple web application that gives you a tool to work with cinema tickets. 
	To access the system you should log in providing your login and password. 
	In case you do not have your personal account in the system registration steps are required or you can 
	use default user credential: login: admin, password: admin.

                                  STARTING APPLICATION
	  As this application by default uses MySql server you need to install and launch it. 
	Also it is required to create a data base with name ‘sergii_stets_tickets_booking’ and run SQL scripts 
	from db/scripts/mysql folder from the application to initialize data base.
	Build war file with the help of Maven and deploy it on your Tomcat Server. 
	After that application will be available at the next link in your browser: localhost:8080/tickets-booking/

                                    TECHONOLGY STACK
	  Application is written in Java building with Maven and deploying to Tomcat Server.  
	It also uses Spring Framework for IoC. MVC .There are several implementations of data manipulation. 
	The default implementation that  works with MySQL consists of services that support transactions and 
	DAO classes that works with JDBC template.Some steps are  required to change this default behavior. 
	In details: 
	1) If you want to work with the application using Java maps as a data storage you have to uncomment 
  	@Repository annotations in the classes inside 'emap.cdp.spring.dao.map.*
  	package and comment the same annotation in the classes inside emap.cdp.spring.dao.db.* 
  	package. The same is required for a service classes: 
  	you need to uncomment @Service annotation in the classes inside emap.cdp.spring.service.base.* 
  	package and comment the same annotation in the classes inside emap.cdp.spring.service.base.* package.
  	2) If you want to use in memory data base you just need to uncomment jdbc:initialize-database tag inside 
  	spring/data-source-context.xml to run SQL scripts automatically and uncomment HSQL configuration 
  	inside configuration.properties and comment MySQL configuration.

