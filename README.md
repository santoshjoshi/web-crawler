# About the application:

The application is all about harnessing the capabilities of below components to create a web crawler that can crawl any site.  
So here we are mainly interested in getting the pin code of various areas/cities across India from a site called javataskforce.com.
	
## This Application uses:
		
* **Apache CXF**:   
  Making REST/HTTP requests to a remote site and fetching the page.
* **JPA/Hibernate with HSQLDB**:  
  Using JPA with Hibernate to populate the HSQLDB with the information we are interested in.
* **Apache Camel**:  
  Coordinating between CXF/JPA, leveraging many tasks that require a lot of coding, creating routes that state what to do and when to do.


## Overview:

The whole application is interconnected by Camel routes, which are as follows (all routes are defined in camel-routes.xml)
	
* **states-invoke-route**

    - invoke the crawler service to get state
    - parse/process the states obtained
    - put them in another Queue
    - store the states into HSQLDB
		
* **districts-invoke-route**

    - pool the DB (HsqlDb) and get the State with some particular status (INITIAL)
    - update the status of State to (IN_PROCESS)
    - invoke the crawler service to obtain districts for state
    - parse/process the districts obtained
    - put them in another Queue
    - store the districts into HSQLDB
    - mark the state as processed (COMPLETED)
		
* **pincode-invoke-route**

    - pool the DB (HsqlDb) and get the District with some status (INITIAL)
    - update the status of District to (IN_PROCESS)
    - invoke the crawler service to obtain areas/cities for the fetched District
    - parse/process the areas/cities obtained
    - put them in another Queue
    - store the area/city into HSQLDB
    - mark the district as processed (COMPLETED)
	

In a nutshell, there are some routes that invoke a crawl URL, and the result is parsed/processed and then saved into the crawl Database.
	
	
## Configuring the application:

**Scheduling configuration:**

- Open `camel-routes.xml` and change the cron statement to a suitable time: 
   quartz://myGroup/myTimerName?cron=30 06 00 03 08 2016
- Open the `camel-routing-policy.xml` and schedule time for:
- `districts-invoke-route`
- `pincode-route-policy`
  		
  		
## Running the application:

1. Run the following commands:
   mvn clean install
2. Run the HSQL Database.
3. Go to the `hsqldb` folder:
- On Windows:
  - Click `db_start.bat`.
- On Unix/Linux:
  - Execute `sh db_start.sh`.
4. Execute the following command to run the application:


## Monitoring:

- The application can be monitored using JConsole at port `86764` (the port can be changed by changing the JMX setting in `pom.xml`):
```
-Dcom.sun.management.jmxremote
-Dcom.sun.management.jmxremote.authenticate=false
-Dcom.sun.management.jmxremote.ssl=false
-Dcom.sun.management.jmxremote.port=8764
```

Open JConsole and add the following connection string:

```
service:jmx:rmi:///jndi/rmi://127.0.0.1:8764/jmxrmi
```
- Monitoring HSQL DB:
- Windows Client:
  - Execute `db_client.bat` available in the `hsqldb` folder.

 
