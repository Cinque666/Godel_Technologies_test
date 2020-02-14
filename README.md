# INSTALLATION AND RUNNING

1. Clone repository
-git clone *URL*;
2. Open it in IDE;
3. Download maven dependencies;
4. Create file in resources directory "database.properties"
Fill it like an example:  
database.url = jdbc:postgresql://localhost:5432/*DB_NAME*?createDatabaseIfNotExist=true&useSSL=false  
database.username = *USERNAME*  
database.password = *PASSWORD*  
database.driver_class = org.postgresql.Driver
5. Compile it and pack to war file
Maven -> clean install;
6. Download Tomcat: https://tomcat.apache.org/download-80.cgi;
7. Make sure that port: 8080 is free or change it;
8. Start Tomcat *catalina.bat /start*
9. Deploy it.

# HOW TO USE
1. Open main page;
2. Choose fileds to write the information;
3. Write the information into field(fields);
4. If two fields have wrong information it will be an error message;
5. If one of them is correct you will be redirecting to page with data table;
