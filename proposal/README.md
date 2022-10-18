# Parking Management System

Group 6

Group Members: Akshit Sharma, Sarthak Dhomne, Manomaysubban Narasimha

Group members working on the proposal: Akshit Sharma, Sarthak Dhomne, Manomaysubban Narasimha

## Problem Statement:

- Finding a vacant spot is very tough for customers.

- Payment is not as easy, since many modes of payment are not accepted.

- Some parking lots are not designed to accomodate all types of vehicles.

## Description:

Our parking management system ensures that customers can find vacant spots with ease, and alerts them if all slots are full so that they can move on to another floor or garage. Vehicles of all types and sizes are accomodated. Payment methods of all kinds are accepted and easy to use. 

## Functionalities:

We will focus on the following set of requirements while designing the parking garage:

- The parking garage would consist of multiple floors for customers to park their vehicles.

- The parking garage would have several entry and exit points.

- People can get a parking ticket from the entry points and can pay the fee at the exit points while leaving.

- People can pay for the tickets at the automated exit panel or to the parking attendant.

- People can choose to pay via either cash or credit cards or debit cards or Apple pay or Google pay.

- Each floor would consist of customer's info portal where the customers can pay for the ticket and in this case, those customers would not have to pay at the exit points.

- The system should not allow more vehicles than the maximum capacity of the parking lot. If the parking is full, the system should be able to show a message at the entrance panel and on the parking display board on the ground floor.

- Each parking floor will have many parking spots. The system should support multiple types of parking spots such as Compact, Large, Handicapped, Motorcycle, etc.

- The Parking lot should have some parking spots specified for electric cars. These spots should have an electric panel through which customers can pay to charge their vehicles.

- The system should support parking for different types of vehicles like car, truck, van, motorcycle, etc.

- Each parking floor should have a display board showing any free parking spot for each spot type.

- The system should support a per-hour parking fee model. For example, customers have to pay $4 for the first hour, $3.50 for the second and third hours, and $2.50 for all the remaining hours.

## Actors:

- Admin: 
       Mainly responsible for adding and modifying parking floors, parking spots, entrance, and exit panels, adding/removing parking attendants, etc.

- Customer: 
       All customers can get a parking ticket and pay for it.

- Parking attendant: 
       Parking attendants can do all the activities on the customer’s behalf, and can take cash for ticket payment.

- System: 
       To display messages on different info panels, as well as assigning and removing a vehicle from a parking spot in a particular floor.

## Operations:

- Add/Remove/Edit parking floor: To add, remove or modify a parking floor from the system. Each floor can have its own display board to show free parking spots.

- Add/Remove/Edit parking spot: To add, remove or modify a parking spot on a parking floor.

- Add/Remove a parking attendant: To add or remove a parking attendant from the system.

- Take ticket: To provide customers with a new parking ticket when entering the parking lot.

- Scan ticket: To scan a ticket to find out the total charge.

- Credit card payment: To pay the ticket fee with credit card.

- Debit card payment: To pay the ticket fee with debit card.

- Cash payment: To pay the parking ticket through cash.

- Add/Modify parking rate: To allow admin to add or modify the hourly parking rate.

## Assumptions:

- Gas vehicles are not parked in spots for electric vehicles.

- Other vehicles are not parked in spots for motorcycle parking.

- Handicap parking rules are followed.

- Vehicles are parked properly and don't take up multiple spaces.

## Intended Usage:

- Users of this parking system are any people seeking parking for their vehicles, such as civilians, visitors, employees, and students.

- The system is designed to be implemented in a variety of general parking lot settings, especially those with parking garages of multiple floors.

- Example use cases: University parking, Mall parking, General City/Downtown parking

## Operating Environment:

- Ran through oracle cloud and each process is in its own seperate Docker Container with appropriate names.


## References:

[1] https://www.geeksforgeeks.org/design-parking-lot-using-object-oriented-principles/

[2] https://www.javatpoint.com/parking-lot-design-java

[3] https://leetcode.com/discuss/interview-question/124739/Parking-Lot-Design-Using-OO-Design

[4] https://www.youtube.com/watch?v=DSGsa0pu8-k

[5] https://www.youtube.com/watch?v=tVRyb4HaHgw

[6] https://www.educative.io/courses/grokking-the-object-oriented-design-interview/gxM3gRxmr8Z
