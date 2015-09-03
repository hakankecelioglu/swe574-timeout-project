**First Way**

In order to run the backend code in local machine:

- In "TimeoutBackend" root directory, run "mvn install" and "mvn package"

- In persistence.xml file, enter your database credentials

- Create a new database, "demo".

- Run the project


---

**Second Way**

Run Backend Locally

In order to install and run timeout backend on your local computer, first you should pull the last version of the backend project from git. Then the following steps must be followed;

  1. Enter into folder “/project\_folder/TimeoutBackend/TimeoutBackend”. “project folder” should be replaced with the path of your git repository.
  1. Rename pom.xml file as pom\_appEngine.xml.
  1. Rename pom\_local.xml file as pom.xml.
  1. Open eclipse, right click  and choose “Import->Existing Maven Projects”.
  1. Browse related folder which is “/project\_folder/TimeoutBackend/TimeoutBackend”. “project folder” should be replaced with the path of your git repository.
  1. When project is added to your eclipse, open src/main/java/common/DBUtility.java class. Comment out the lines shown below using single line comment (“//…”) or multi line comment (“/**…**/”);
```
// import com.google.appengine.api.utils.SystemProperty;
// For Cloud Usage
// if (SystemProperty.environment.value() == // SystemProperty.Environment.Value.Production) {
// 	properties.put("javax.persistence.jdbc.driver",
// 			"com.mysql.jdbc.GoogleDriver");
// 	properties.put("javax.persistence.jdbc.url",
// 			"jdbc:google:mysql://vernal-day- 88222:instance2/demo?user=root");
// 	return properties;
```
  1. Then, mysql server should be started using port 3306. Demo database should be created and then the sql command shown below should be run;
```
GRANT ALL PRIVILEGES ON demo.* TO 'root'@'localhost' IDENTIFIED BY 'password' WITH GRANT OPTION;
```
  1. If you are using Mac and MAMP for mysql, default user is “root” and default password is “root” as well. After running the command above, you can get error message while trying to connect phpmyadmin again. To handle that error, open terminal and write “nano /Applications/MAMP/bin/phpMyAdmin/config.inc.php”. Search “password” pressing CTRL+W, and change the line shown below. Change ‘root’ as ‘password’.
```
$cfg['Servers'][$i]['password']      = 'rootpassword';
```
  1. After that, the last step is running Application.java on Eclipse as Java Application. Open a browser and go http://localhost:8080/.
  1. If you see the following three lines in eclipse console, congratulations!!! You have been successfully installed backend on your computer.
```
Hibernate: select max(id) from Greeting
Hibernate: insert into Greeting (author, content, date, id) values (?, ?, ?, ?)
Hibernate: insert into Greeting (author, content, date, id) values (?, ?, ?, ?)
```
If you have any questions, ask to group via whatsapp or wait till Monday for next meeting.

Do not commit these changes on backend folder!!! If you want, you can copy TimeoutBackend folder out of the git repository. So, your git repository will look like no changes done.