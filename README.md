# SEP
student personal loan application system

This is the github for the 48440 Software Engineering Practice Tut8 group 5 assessment.

#Configurations
--The configuration is based on Intellj
==Local database configuration on linux
1. Install mysql-server, set up password and user name.
2. Start the server using command "sudo service mysql status"
3. Get into mysql by using command "mysql -u [your user name] -p"
4. Create your own database by command "create database [your database name]"
5. Import the data from the uts_loan.sql file, "mysql>source [location path of the file]/uts_loan.sql"
If using windows, you can just download mysql and navicat and set up username and password. 
Open navicat GUI, create your local database and import the sql file into your own database.

==Server
Download tomcat server

==Project
1. In Intellj, use File->Open Project to open the base directory.
2. If the Intellj doesn't detect Maven, right click the "pom.xml" file to add the Maven.
3. Use "mysql>SELECT @@version;" to check your version of mysql-server.
4. Open "pom.xml", change the version of mysql-connector-java, to be the same with your sql-version.
5. Click "Persistence" in the right tool bar, choose assign data source, add mysql, and enter details of your local database.
