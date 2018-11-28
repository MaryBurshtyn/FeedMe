# Test plan

## Content
1 [Introduction](#1)</br>
2 [Test Items](#2)</br>
3 [Risk Issues](#3)</br>
4 [Features to be tested](#4)</br>
5 [Test Approach](#5)</br>
6 [Results](#6)</br>
7 [Conclusion](#7)</br>

 
# 1 <a name = "1"> Introduction </a>
This document is designed to test the mobile application "FeedMe". </br>
The purpose of the test is to check the functionality and suitability of the application for practical use.

# 2 <a name = "2"> Test Items </a></br>
Objects of testing are functional testing and usability testing. 
The attributes of quality include the following characteristics: 

- Functional completeness
- Functional correctness
- Operability
- User error protection
- User interface aesthetics</br>

# 3 <a name = "3"> Risk Issues </a></br>
Feed function works only if the user has an Internet connection and MQTT-broker works. User's mobile phone should have enough memory to 
save history of feeding.The current version of the smartphone operating system should be Android 6.0+.

# 4 <a name = "4"> Features to be tested </a>
In the process of testing it is supposed to check the compliance of the system with the requirements on the basis of which it was designed and implemented. </br>
According to the [System Requirements Document](Documentation/SRS.md) thre are functional and  non-functional requirements to be tested.</br>
For this project, the main functions are as follows:

1. Sign up to the application</br>
2. Feed</br>
3. Choose portion to feed</br>
4. Choose history time interval</br>
5. Show history</br>
6. Choose analysis time interval</br>
7. Choose analysis criterion</br>
8. Show statistics</br>
9. Choose pet</br>
10. Name pet</br>
11. Turn on/off notifications</br>
12. Sound on/off</br>
13. Turn on/off auto mode</br>
14. Auto mode</br>
15. Add next food</br>
16. Show last food</br>

It is also important to check correctness of non-functional requirements:

- Usability:
1. Main function of application is feeding that's why button "Feed" will be contrast and will be differ from other elements.</br>
2. All functional elements of the user interface will have names describing the action that the element does.</br>
3. Principle of least effort: to feed pet user should do only 3 steps.</br>
- Security:
1. To sign up the application user should enter unique username and password which is written on the case of device.
- External interfaces:
1. Main function "Feed" will not be able without Internet connection.

FeedMe application is native android application. There are some features of android native applications to be tested:
1. Application should have the same layouts on different devices.
2. Application should respond on incoming interrupts (calls, SMS).
3. Installing and running the application.

# 5 <a name = "5"> Test Approach </a>
This application will be tested manually.

# 6 <a name = "6"> Pass / Fail Criteria </a>
| ID | Title | Scenario |Expected Result|Actual result|Pass/Fail indication|
|:---|:---|:---|:---|:---|:---|
| 1 | Sign up to the application |<br>1. Launch application for the first time </br><br>2. Enter "Mary" in username field and "qwerty" in the password field (That information should be written on the case of device.).</br> <br>3. Click on "Sign up" button</br>|Application should register user and open next screen to set pet name and kind of animal(look State Machine Diagram in [SDS](Documentation/SDS.md)).| User was registered and set pet screen was opened. | Pass |
| 2 | Security |<br>1. Launch application for the first time </br><br>2. Enter any username in username field and any password in the password field.</br> <br>3. Click on "Sign up" button</br>|Application will not open next screen to set pet name and kind of animal and show user "Try one more time" message.| Application showed error message: "Try one more time". | Pass |
| 3 | Feed |<br>1. Launch application. If it is not first enterance application will open "Feed screen"(look Glossary in [SDS](Documentation/SDS.md))</br><br>2. Enter size of portion more than 0 and less than 6 in the portion field.</br> <br>3. Click on "Feed" button</br>|Application should show "Pet feed successfully" message.| Application showed message: "Pet feed successfully". | Pass |
| 4 | Usability: Principle of least effort |<br>1. Launch application. If it is not first enterance application will open "Feed screen"(look Glossary in [SDS](Documentation/SDS.md))</br><br>2. Enter size of portion more than 0 and less than 6 in the portion field.</br> <br>3. Click on "Feed" button</br>|User should do only 3 steps to feed pet.| Action took three steps to complete. | Pass |
| 5 | Feed(wrong portion) |<br>1. Launch application. If it is not first enterance application will open "Feed screen"</br><br>2. Enter size of portion less than 1 or more than 5 in the portion field.</br> <br>3. Click on "Feed" button</br>|If entered portion less than 1 application should show "Enter portion more than zero." if entered portion more than 5 application should show "Large portion" message.| Applicaion showed "Enter portion more than zero." message when size was 0 and "Large portion" message when it was more than 6. | Pass |
| 6 | External interfaces |<br>1. Turn off the Internet.</br> <br>2. Launch application. If it is not first enterance application will open "Feed screen"</br><br>3. Enter any size of portion in the portion field.</br> <br>3. Click on "Feed" button</br>|Application should show "Can't feed pet. No Internet access" message.| Application showed error message: "Can't feed pet. No Internet access" | Pass |
| 7 | Choose portion to feed |<br>1. Launch application.</br> <br>2. Choose size of portion and enter them to the portion field. </br><br>3. Click on "Feed".</br>|If portion is correct and there is Internet access application should show choosed size of portion in History.| Added portion was showed in History page. | Pass |
| 8 | Choose history time interval |<br>1. Launch application.</br> <br>2. On the opened "Feed screen" slide to the right to open navigation menu.</br><br>3.Click on "History" button.</br><br>4. To choose history time interval click on "Day", "Week" or "Month" button. </br>|Application should show history of feeding for choosed time interval.| Application showed history of feeding for choosed time interval | Pass |
| 9 | Show history |<br>1. Launch application.</br> <br>2. On the opened "Feed screen" slide to the right to open navigation menu.</br><br>3.Click on "History" button.</br>|Application should show history of feeding of current day.| Application showed history of feeding for current day. | Pass |
| 10 | Choose analysis time interval |<br>1. Launch application.</br> <br>2. On the opened "Feed screen" slide to the right to open navigation menu.</br><br>3.Click on "Analysis" button.</br><br>4. To choose analysis time interval click on "Week", "Month" or "Year" button. </br>|Application should show diagrams of feeding for choosed time interval.| Application showed diagram of feeding for choosed time interval. | Pass |
| 11 | Choose analysis criterion |<br>1. Launch application.</br> <br>2. On the opened "Feed screen" slide to the right to open navigation menu.</br><br>3.Click on "Analysis" button.</br><br>4. To choose analysis criterion click on one of the following buttons: "Week", "Month","Year". </br><br>5.To chooseanalysis criterion click on "Food" or "Money" button.</br>|Application should show diagrams of feeding for choosed criterion.| Application showed diagram for choosed criterion. | Pass |
| 12 | Show statistics |<br>1. Launch application.</br> <br>2. On the opened "Feed screen" slide to the right to open navigation menu.</br><br>3.Click on "Analysis" button.</br><br>4. Click on one of the following buttons: "Week", "Month","Year". </br></br>|Application should show diagrams of feeding.| Application showed diagram of feeding. | Pass |
| 13 | Choose pet |<br>1. Launch application.</br> <br>2. Sign up to the application.</br><br>3.At the "Pet Screen" choose one of the following kind of animal: cat, dog, ferret, hamster or rabbit by clicking on previous or next arrows. </br><br>4. Click on the "Next" button. </br></br>|Application should show choosed pet on the "Feed screen" as image.| Application showed choosen pet on the "Feed screen". | Pass |
| 14 | Name pet |<br>1. Launch application.</br> <br>2. On the opened "Feed screen" slide to the right to open navigation menu.</br><br>3.Click on "Settings" button.</br><br>3. At the "Settings screen" click on text opposite to the "Name" text.</br><br>4. At opened dialog window enter your pet's name</br><br>5. Click on "Change pet name" button</br>  |Application should show pet name opposite to "Name" text at "Settings screen" and at the "Feed screen" under the image of choosed pet.| Application showed new pet name at the "Settings screen" and at the "Feed screen" | Pass |
| 15 |Turn on/off notifications |<br>1. Launch application.</br> <br>2. On the opened "Feed screen" slide to the right to open navigation menu.</br><br>3.Click on "Settings" button.</br><br>3. At the "Settings screen" click on switch opposite to the "Notifocations" text.</br> |Application should: <br> 1. turn off notifications</br> <br>2. show on or off notifications at the "Settings screen" </br> </br>3. shouldn't notify about fedding if next food notification is set.| Application sent notification immediately even if it was seted at later time. "Notifocations" switcher sets to same value as "Sound" switcher after return to the "Settings screen". | Fail |
| 16 |Sound on/off|<br>1. Launch application.</br> <br>2. On the opened "Feed screen" slide to the right to open navigation menu.</br><br>3.Click on "Settings" button.</br><br>3. At the "Settings screen" click on switch opposite to the "Sounds" text.</br> |Application should: <br> 1. turn on/off sounds when click on buttons.</br><br>2. show on or off sound at the "Settings screen" </br> | Application turnded off sound. "Sound" switcher seted at correct value after return to the "Settings screen". | Pass |
| 17 |Turn on/off auto mode|<br>1. Launch application.</br> <br>2. On the opened "Feed screen" slide to the right to open navigation menu.</br><br>3.Click on "Settings" button.</br><br>3. At the "Settings screen" click on switch opposite to the "Auto mode" text.</br> |Application should: <br> 1. turn on/off auto mode</br> <br>2. show on or off auto mode at the "Settings screen" </br> </br>3. If  auto mode is turned off application shouldn't notify about fedding if next food notification is set and feed pet automatically.| Application sent notification immediately even if it was seted at later time. | Fail |
| 18 | Add next food |<br>1. Launch application. If it is not first enterance application will open "Feed screen"</br><br>2. Enter time for netx feeding at the "Next food" field.</br> <br>3. Click on "Add" button</br>|Application should:<br>1.if notifications is turned on - at set time notify user to feed pet. </br><br>2. if notificatios if turned off and auto mode is turned on - application shouldn't show notifications and feed pet automtically</br>| Application sent notification immediately even if it was seted at later time. | Fail |
| 19 | Show last food |<br>1. Launch application. If it is not first enterance application will open "Feed screen"</br>|Application should show last time of feeding at "Feed screen".| Application showed new last time of feeding at "Feed screen". | Pass |
| 20 |Usability |<br>1. Launch application.</br><br>2. Look through all application.</br>|<br>Button "Feed" should contrast and will be differ from other elements.</br><br>All functional elements of the user interface should have names describing the action that the element does.</br>| Button "Feed" is contrast and differ from other elements. All functional elements have names describing the action that the element does. | Pass |
| 21 |The same layouts on different devices |<br>1. Install application to another device.</br><br>2. Look through all application.</br><br>3. Check all layouts.</br>| Application should have the same layouts on current devices. ||  |
| 22 |Incoming interrupts |<br>1. Launch application.</br><br>2. Call up the phone on which application is launched.</br>| Application should accept incoming call without closing the application.|| |
| 23 | Installing and running the application. |<br>1. Download apk file and click on install.</br><br>2.Run installed application.</br>| Application should be installed and ran successfully if device and OS is met the system requirements. || |


# 7 <a name = "7"> Conclusion </a>
Application "FeedMe" was tested and 3 of 20 tests was failed. But presumably all these failures was caused by only one problem: wrong setting up of notification. All other 17 tests was successfully passed.

Also was tested additional function: change cost of food. It was failed cause after cost was changed all statistic changed according new cost too. But (as suggestion) it may not necessarily be removed, it can be represented as optional change of cost if it was set incorrectly previously but of course with completing with correct variant of function.

Another suggestion - decrease welcome screen show time. From 4 sec to 2 sec.
