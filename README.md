About the application :
-----------------------------------------


The application is all about harnessing the capabilities of below components to create a web crawler that can crawl any site.  
So here we are mainly interested in getting the pin code of various areas/cities across India from a site called javataskforce.com.
	
#####This Application uses:
		
* #####Apache CXF:
   
  &nbsp;&nbsp;&nbsp;making rest/http request to remote site and fetch the page.
* #####JPA/Hibernate with HSQLDB

  &nbsp;&nbsp;&nbsp;using japa with hibernate to populate the HSQLDB with the information we are interested in  
* ####Apache Camel
  
  &nbsp;&nbsp;&nbsp;coordinating between cxf/jpa, leveraging many task that requires a lot of coding, creating routes that states what to do and when to do


#####Overview:

  The Whole application is interconnected by camel routes, which are as follows (all routes are defined in camel-routes.xml)
	
* #####states-invoke-route

		i)   invoke the crawler sevice to get state
		ii)   parse/process the states obtained
		iii) put them in another Queue
	        iv)  stoe the states into HSQLDB
		
* #####districts-invoke-route

		i)   pool the DB( HsqlDb) and get the State with some particular status(INITIAL)
		ii)  update the status of State to (IN_PROCESS)
		iii) invoke the crawler service to obtain districts for state
		iv)  parse/process the districts obtained
		v)   put them in another Queue
		vi)  store the districts into HSQLDB
		vii)  mark the state as processed (COMPLETED)
		
* #####pincode-invoke-route
		i)   pool the DB( HsqlDb) and get the District with some status(INITIAL)
		ii)  update the status of District to (IN_PROCESS)
		iii) invoke the crawler service to obtain areas/cities for the fetched District
		iv)  parse/process the areas/cities obtained
		v)   put them in another Queue
		vi)  store the area/city into HSQLDB
		vii)  mark the district as processed (COMPLETED)
	

In a nutshell there are some routes which invokes a crawl url , the result of which is parsed/processed and then
saved into the crawl Database
	
	
Configuring the application:
	a) scheduling configuation:
		a) open camel-routes.xml and  change the cron in statement to a suitable time 
			quartz://myGroup/myTimerName?cron=30 06 00 03 08 ? 2016
		
		b) open the camel-routing-policy.xml and  schedule time for
			i)  districts-invoke-route
			ii)  pincode-route-policy
			
			
Running the application:

	a) mvn clean install
	b) run the HSQL Database 
	c) Go to hsqldb folder  
		  on Windows:
		  		i)  click db_sart.bat
		  on Unix/Linux
		  		i)  sh  db_start.sh
		  		
   d) mvn exec:exec	  		
		this will execute com.javatask.Run
		
Monitoring :
	a) Application can be monitored using Jconsole at port 86764( port can be changed by changing the jmx setting in pom.xml)
		<argument>-Dcom.sun.management.jmxremote</argument>
		<argument>-Dcom.sun.management.jmxremote.authenticate=false</argument>
		<argument>-Dcom.sun.management.jmxremote.ssl=false</argument>
		<argument>-Dcom.sun.management.jmxremote.port=8764</argument>
	
		open Jsonsole and add
	 		service:jmx:rmi:///jndi/rmi://127.0.0.1:8764/jmxrmi
	 	
	b) Monitoring HSQL DB
			- Windows Client ( 
		   		execute db_client.bat available in hsqldb folder
	   		
	   		 	
	 	
	
	
