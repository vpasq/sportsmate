![Readme image](readme.png)

### SportsMate ([UML Diagram](https://github.com/vpasq/sportsmate/blob/master/SportsMate_UML.pdf)) 
A Java Maven Project of a sports media application with a focus on basketball.

### The Product Vision 
For lots of basketball players or amateurs who need related resources of basketball to play, 
the Sportsmate is a basketball social app that provides gym reserving, team system, and match 
function that provide a chance to know people online and play basketball at real basketball court. 
Unlike other basketball social app or basketball equipment sales app, OUR product provides the 
chance to let basketball lovers talk in the real world. 

### Setup the MySQL Database
```bash
1. Install MySQL
2. Log into MySQL as the root user: (See Handy MySQL Tips Below)
3. Create a new database user:  CREATE USER 'demo'@'localhost' IDENTIFIED BY 'demo';
4. Grant access to new database user:  GRANT ALL ON sportsmate.* TO 'demo'@'localhost';
5. List privileges granted to new database user:  SHOW GRANTS FOR 'demo'@'localhost';  
4. Log out of MySQL by typing: \q
```

### Handy MySQL Tips and Commands
```bash
- In MySQL, by default, the username is root and there is no password
  (just hit enter when asked)
- Under MAMP / MAMP Pro, by default, the username is root and the password is root
- Log into MySQl as the root user with default password:  mysql -h localhost -u root -p
- Log into MySQl as the root user using MAMP / MAMP Pro:  mysql -h localhost -u root -proot
- Drop new user created above: DROP USER 'demo'@'localhost';
```


### Clone the sportsmate repository and run the project using Maven
```bash
1. git clone https://github.com/vpasq/sportsmate.git
2. cd sportsmate
3. mvn clean compile
4. mvn exec:java -Dexec.mainClass=sportsmate.MainApp
```

### Version
1.0.0


