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

# 5 <a name = "5"> Test Approach </a>
This application will be tested manually.

# 6 <a name = "6"> Pass / Fail Criteria </a>
Results of testing are represented in [Testing Results](Documentation/TestingResult.md) document.
# 7 <a name = "7"> Conclusion </a>
Application "FeedMe" was tested and 3 of 20 tests was failed. But presumably all these failures was caused by only one problem: wrong setting up of notification. All other 17 tests was successfully passed.

Also was tested additional function: change of food cost. It was failed cause after cost was changed all statistic changed according new cost too. But (as suggestion) it may not necessarily be removed, it can be represented as optional change of cost if it was seted incorrectly previously but of course with completing with correct variant of function.

Another suggestion - decrease welcome screen show time. From 4 sec to 2 sec.
