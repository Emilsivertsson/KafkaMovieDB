### Your favorite movies!
# A Rest API, Kafka and MySQL project

# Table of Contents
- [Description](#Description)
- [What was your motivation?](#What-was-your-motivation?)
- [Installation and Usage](#Installation-and-Usage)
- [Credits](#Credits)
- [Tutorials](#Tutorials)
- [License](#License)
- [Tests](#Tests)

# Description
This is a School projekt for the course in Integration plattforms. it consists of several moduls.
- A Console application that takes the users input and sends it to a Rest API as a Json String.
- A JavaFX application that takes the users input and sends it to a Rest API as a Json String.
- A Rest API that serves as a Kafka producer and sends the Json String to a Kafka topic.
- A Console application that serves as a Kafka consumer and takes the Json String and prints its information to the user.
- A MySQL database that serves as a Kafka consumer and takes the Json String and saves it to the database.


# What was your motivation?


Why did you build this project? (Note: the answer is not "Because it was a homework assignment.")
What problem does it solve?
What did you learn?



# Installation and Usage
1. To use the application you need to have a local MySQL database running on your computer.
   ⋅⋅*Download MySQL Community from https://dev.mysql.com/downloads/installer/ and install it.
   ⋅⋅*When you have installed MySQL Community you need to create a database called "movies".
2. You also need to have a local Kafka server running on your computer.
   ⋅⋅*Download Kafka from https://kafka.apache.org/downloads and install it.
   ⋅⋅*When you have installed Kafka you need to run the Zookeeper and Kafka server.
   ⋅⋅*Please see the information on the homepage on how to do this.
3. Clone this repository to your computer and open it in your IDE.
   ⋅⋅* First run the Rest API application.
   ⋅⋅* Second run the Console Consumer application.
   ⋅⋅* Third run the Database Consumer application.
   ⋅⋅* Fourth run either the Console Producer or the JavaFX Producer application.

When entered the information in the Producer application you can see the information in the Console Consumer application and in the database.


# Credits
- my classmates Kristoffer and Johan for prof-reading my code and checking that i did not miss anything.
- chatGPT and Bard for helping me when i got stuck.

# Tutorials
- https://youtube.com/playlist?list=PLGRDMO4rOGcNLwoack4ZiTyewUcF6y6BU&si=BByR1u0OqgjQH9TE
- https://youtu.be/EUzH9khPYgs?si=fGigvrOFoLD9ZXpK
- https://youtu.be/B5j3uNBH8X4?si=HF4JNBrfoxOhQD4g

# License
The application is licensed under the MIT license. Please see the license file for more information.



To add a screenshot, create an assets/images folder in your repository and upload your screenshot to it. Then, using the relative filepath, add it to your README using the following syntax:
```md
![alt text](assets/images/screenshot.png)
```



# Tests

