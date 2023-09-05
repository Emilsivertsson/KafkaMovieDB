# Your favorite movies!

### A Rest API, Kafka and MySQL project


## Table of Contents
- [Description](#Description)
- [What was your motivation?](#What-was-your-motivation?)
- [Installation and Usage](#Installation-and-Usage)
- [Dependencies](#Dependencies)
- [Credits](#Credits)
- [Tutorials](#Tutorials)
- [License](#License)
- [Tests](#Tests)

## Description
This is a School projekt for the course in Integration plattforms. it consists of several moduls.
- A Console application that takes the users input and sends it to a Rest API as a Json String \
  and serves as a consumer for a kafka topic.
- A Rest API that serves as a Kafka producer and sends the Json String to a Kafka topic.
- A Console application that serves as a Kafka consumer and takes the Json String and prints its information to the user.
- A MySQL database that serves as a Kafka consumer and takes the Json String and saves it to the database. \
    It also serves as a Kafka producer and sends the information from the database to a Kafka topic.


## What was your motivation?
Our assignment was to create a Rest API that takes the users input and sends it to a Kafka topic.\
Then we were supposed to create a Kafka consumer that takes the Json String and prints its information to the user.\
We were also supposed to create a Kafka consumer that takes the Json String and saves it to a database.\
I wanted to take it a step further and create a JavaFX application that takes the users input and sends it to the Rest API as a Json String.\
I learned alot from this project , and i can see the benefits of using Kafka in a project with multiple applications \
and microservices that needs to communicate with each other.\
i also extended my knowledge about spring boot. I have used it before but not to this extent.\


## Installation and Usage
1. To use the application you need to have a local MySQL database running on your computer.
   - Download MySQL Community from https://dev.mysql.com/downloads/installer/ and install it.
   - When you have installed MySQL Community you need to create a database called "movies".
2. You also need to have a local Kafka server running on your computer.
   - Download Kafka from https://kafka.apache.org/downloads and install it.
   - When you have installed Kafka you need to run the Zookeeper and Kafka server.
   - Please see the information on the homepage on how to do this.
3. Clone this repository to your computer and open it in your IDE.
   - First run the Rest API application.
     - This will start on port 8080, start the API that serves as a Producer for the Topic "movie". 
   - Second run the Console Consumer application.
     - This will start on port 8081, starts an application that serves as a Consumer for the Topic "returningData", with the group-id "Console".
     - It is only function is to print the information that´s saved in the database to the user.
   - Third run the Database Consumer application.
     - This will start on port 8082, starts an application that serves as :
       - a Consumer for the Topic "movie", with the group-id "Database". The information from the Topic is saved to the database.
       - a Producer for the Topic "returningData". The information from the database is sent to the Topic.
   - Fourth run the MovieDBApplication.
     - This will start on port 8083, starts an application that takes the users input, sends it in a POST request to the Rest API as a Json String.
     - It also serves as a Consumer for the Topic "movie" group-id "Application". The information from the Topic is printed to the user.
   

When entered the information in the MovieDBApplication you will get the information printed back via the Kafka Consumer.\
you can also see the information in the database.\
The information from the Database also gets printed to the user via the Console Consumer.\


## Dependencies
All dependencies are included in the pom.xml file.
- Java 17
- org.springframework.boot
- org.springframework.kafka
- org.projectlombok
- org.springframework.boot
- spring-boot-starter-data-jpa 
- org.springframework.boot 3.1.2 
- org.springframework.kafka 
- com.googlecode.json-simple 1.1.1 
- org.apache.httpcomponents 4.5.13 
- org.junit.vintage 
- com.mysqlmysql-connector-j 8.0.33

## Credits
- my classmates Kristoffer and Johan for prof-reading my code and checking that i did not miss anything.
- chatGPT and Bard for helping me when i got stuck.

## Tutorials
- https://youtube.com/playlist?list=PLGRDMO4rOGcNLwoack4ZiTyewUcF6y6BU&si=BByR1u0OqgjQH9TE
- https://youtu.be/EUzH9khPYgs?si=fGigvrOFoLD9ZXpK
- https://youtu.be/B5j3uNBH8X4?si=HF4JNBrfoxOhQD4g

## License
The application is licensed under the MIT license. Please see the license file for more information.



To add a screenshot, create an assets/images folder in your repository and upload your screenshot to it. Then, using the relative filepath, add it to your README using the following syntax:
```md
![alt text](assets/images/screenshot.png)
```



## Tests

