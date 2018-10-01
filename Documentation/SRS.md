# Software Requirements Specification
---
## 1 Introduction
### 1.1 Purpose
  This document is written by business analyst and is intended for software developers , managers and customers to formalize and understand product requirement.
Developers use SRS to carry out tasks according to requirements. Managers use it to monitor progress of the project and to allocate tasks.
Customers should approve this document for futher check of fulfillment of the requirements.
### 1.2 Scope
  Developing application is named as **FeedMe.** It provides an opportunity to feed pet throught the Internet using mobile phone (Android).
  This application is designed for real device which based at your home and connected to the Internet. 


## 2 User Requirements
### 2.1 Software interfaces
  **FeedMe** is dependent component of the system. The application should be able to manage device which is based at user's home. 
  Management should be carried out through the MQTT-broker or server. 
  - MQTT-broker is a [Cloud MQTT](https://www.cloudmqtt.com/) - hosted message broker for the Internet of Things. 
  - **FeedMe** is a appilacion installed on client's phone that provides control. Application will send requests to the MQTT-broker. Broker 
  will send this request to the device and device will execute request.
  - The device represents a board with Internet access and connected feeding mechanism.
   ![system](system.png)
   Diagram of the system.
### 2.2 User Interfaces  
#### Log in or sign up screen.
![logIn](startScreen.png)
#### Screen of the first enterance.
![setUp](firstSetUp.png)
#### Feeding screen.
![main](mainScreen.png)
#### Screen that shows menu bar.
![menuBar](menuBar.png)
#### Settings screen.
![settings](settings.png)
#### History screen.
![history](history.png)
#### Analysis screen.
![analysis](analysis.png)
### 2.3 User Characteristics 
#### 2.3.1 Target Audience
People who bought device and want to have an opportunity to feed their pet being away from home. Mostly they are pet owners.
#### 2.3.2 Required Experience
To use **FeedMe** application users should have some base experience in using Android smartphone like connecting phone to the Internet, 
downloading application from Play Market.
### 2.4 Assumptions and Dependencies
- Application can't feed pet without phone connected to the Internet.
- Application can look through the history of feeding, spent food, changings of pet weight.
- Application can't feed pet without device connected to the Internet, but there is auto mode to feed pet automatically. 
- Application can't feed pet without working MQTT-broker.
## 3 System Requirements

     
