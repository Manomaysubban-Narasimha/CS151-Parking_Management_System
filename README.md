# EasyPark: Parking Management System
## Team 6: Mano Narasimha, Akshit Sharma, Sarthak Dhomne


## Presentation Contributions:

**Mano Narasimha**:
- Created and Presented the following slides and topics: 
- Frontend: Vaadin - explained the use of Vaadin for UI and its advantages
- BackEnd: Springboot - explained advantages and ease of configuration
- Security: Explained benefits of measures like password requirements and using a hashing algorithm
- SHA3-256: Explained in detail about the algorithm, how it works, and its advantages over encryption
- References slide

**Sarthak Dhomne**:
- Created and Presented the following slides and topics: 
- Introduction
- Overview: Application details and high-level goals
- Application Parking Process: the flow of using the application
- Why we chose InfluxDB: time-series data logging, advantages for parking system use case
- Why we chose Docker: containerized app advantages - lightweight, fast, reliable


**Akshit Sharma**:
- Set up and showcased the complete demo of the application:
- Went into detail about the various components of the application
- Showcased homepage, registration, login, main page, etc.
- Presented slides on scope for future additions

## Code Contributions:

**Mano Narasimha:**
- Embedded salt and pepper with SHA3-256 one-way cryptographic hash function in order to slow down brute-force, dictionary, and rainbow table attacks
- Added Microsoft’s secure password rules 
- Made the application detect whether the password that the user entered is amongst the top 100 most commonly used passwords in O(1) time complexity, and prompted the user to re-enter the password
- Made use of loose coupling, abstraction, abstract factory, and data-hiding into the code and made changes to the codebase to reflect Java’s coding conventions. Made the programs more modular to enhance readability and reduce cognitive complexity of code.

**Sarthak Dhomne:**
- Created + updated Vaadin front-end components for the MainView page.
- Embedded Vaadin front-end components and logic for the Register page.
- Added VehicleTypes selection options
- Implemented Password Exceptions for strong password Registration
- Updated registration fields not matching messages and PasswordFields
- Implemented Password Requirements logic during the login process
- Vaadin fonts and layout edits across the project
- Leave button front-end component

**Akshit Sharma:**
- Updated Influx Handler to store vehicle type, license plate #, etc.
- Implemented MainView layout and components
- Set up Spring boot with vaadin and its dependencies
- Created first versions of the MainView file, HomePage, InfluxHandler, Login, and Register
- Added Docker-compose.yml to run grafana prometheus
- Added nginx.conf so that you can type localhost/grafana and localhost/prometheus to go to their respective locations
- Created the monitoring folder with the correct alert rules so that we know when a container stops running
- Created the grafana dashboard
- Tied the front end to the back end to and then finally to the database. 
- Created the Percentage Full and the logic behind it
- Added the logic for the Leave button. 
- Changed the index.html file to have a universal background color. 

## Updated Diagrams:

















## Functionality:
Our solutions tackles issues that can hinder parking systems, such as payment, secure registration, and parking spaces available. Users must register for an account with their vehicle plate number and a strong password, as well as specify their type of vehicle. Their portal upon logging in allows users to see the % of garage spaces that are occupied, and they are able to directly pay for their parking.

## Operations:

**Customers:**
Register account with vehicle type, vehicle plate #, and valid password
Login with plate # and password
Pay for parking given parking availability
Leave the garage at request by pressing ‘leave garage’

**Admins:** 
Monitor registered users and vehicles
Monitor influxDB timing logs

## Solution:
We solved the complications of parking by implementing a user portal for each customer to interface with. The portal shows users the garage’s parking availability, shows user information, gives the payment option, and allows them to specify when they leave. By consilidating this information through user portals, and allowing admins o monitor time-series parking data for the users, parking systems can be simplified. 

## Steps to Run Code:
Install Docker
Have Docker running
Run the program with the files


## Snapshots of Running Program:
![1](https://user-images.githubusercontent.com/104649639/205762670-8f5942f0-b88d-4643-85c2-1ba7f266c806.png)

Checks whether the entered password is a common password:

![2](https://user-images.githubusercontent.com/104649639/205762718-5bc23078-6def-491b-9d5b-7979b326a373.png)



Password fields do not accept null values:
![3](https://user-images.githubusercontent.com/104649639/205762745-05974931-178f-40b5-ad11-038c0c77e41d.png)


Password must be at least 14 characters long:



![4](https://user-images.githubusercontent.com/104649639/205762768-ec2c6cd5-9dbc-48de-8f4f-dc82a0ce7b2b.png)



Password should have at least one number:


Password must contain special characters:


Password must contain upper case letter(s):


Password must contain lowercase letter(s):








Login page:

After logging in shows user info:








## OOPs concepts applied:

**MVC:** 

Since we are using Spring Boot in the backend, we are inherently making use of MVC    
          design pattern

**Abstract factory:** 

Java’s MessageDigest class has a method called getInstance() which must 
       be overridden by implementing subclasses, so by calling the getInstance()   
        method, we made use the abstract factory design pattern.

**Encapsulation:** 

Data-hiding was achieved via private instance variables and public interface 
  (getters and setters were created and used as needed).

**Abstraction, and loose coupling:** 
The implementation details of algorithms were hidden to 
other classes using appropriate private and package-private access modifiers for all instance variables and applicable methods, thus providing loose coupling as the method’s caller need not change their code to call the method, while the underlying algorithm that is backing up the code can be swapped. For example, the call to getHashedPassword() method of the SecuredPasswordHasher class need not change anywhere, but the underlying algorithm that is used by getHashedPassword() can be changed (for instance, from SHA3-256 + salt + pepper to Argon2di). 

**Exception handling and inheritance:** 
Exception handling was used in order to display error 
messages for the user when the password did not match the requirements. The exception classes made use of inheritance as all of them were checked exceptions that extended the PasswordException class which ultimately extended the Exception super class which extends Object class.

**Threads:** 
Thread.sleep(2000); // in Register.java makes the thread stop for 2 seconds each time 
    the license plate number and password are updated in the database


**References:**

[1] https://www.geeksforgeeks.org/store-password-database/

[2] https://www.baeldung.com/cs/hashing-vs-encryption

[3] https://ico.org.uk/for-organisations/guide-to-data-protection/guide-to-the-general-data-protection-regulation-gdpr/encryption/what-types-of-encryption-are-there/

[4] https://vaadin.com/

[5] https://www.baeldung.com/spring-boot-vaadin

[6] https://spring.io/projects/spring-boot

[7] https://www.docker.com/

[8] https://www.javaguides.net/p/spring-boot-developer-guide.html

