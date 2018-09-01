# SEP
student personal loan application system

This is the github for the 48440 Software Engineering Practice Tut8 group 2 assessment.

#Version of Tools
1. MYSQL: version 8.0.12
2. TOMCAT: 8.0
3. IDE: Intellj
4. Java: Oracal JDK 1.8
5. Maven: 3.3.9

#Complete functions in current stage.
1. Log in and log out function for student, staff, and system admin(No error message diplayed yet when wrong information are entered).
2. View application function for student.
3. Create application function for student.
Hint: to test log in and log out, account detail can be found in the database file.

#Configurations
--The configuration is based on Intellj
==Local database configuration on linux
1. Install mysql-server, set up password and user name.
2. Start the server using command "sudo service mysql status" or "sudo service mysql start"
3. Get into mysql by using command "mysql -u [your user name] -p"
4. Create your own database by command "create database [your database name]". Here we create our database using the same name: "uts_loan".
5. Import the data from the uts_loan.sql file, "mysql>source [location path of the file]/uts_loan.sql"
If using windows, you can just download mysql navicat and set up username and password. 
Open navicat GUI, create your local database and import the sql file into your own database.

==Server
Download tomcat server, extract them or install them in a location, remember the path of that location for further usage. 

==Project
1. In Intellj, use File->Open Project to open the base directory.
2. If the Intellj doesn't detect Maven, right click the "pom.xml" file to add the Maven.
3. Use "mysql>SELECT @@version;" to check your version of mysql-server.
4. Open "pom.xml", change the version of mysql-connector-java, to be the same with your sql-version.
5. Click "Persistence" in the right tool bar, choose assign data source, add mysql, and enter details of your local database.
6. Click "Edit configuration", select Tomcat as your server, and assign the tomcat path, add war-exploded.
7. Click run to start the project.
8. The url is localhost:8080 (If you didn't change your port.)
