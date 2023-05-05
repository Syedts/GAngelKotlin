# GAngelKotlin
Guardian Angel is a secure Home Monitoring Application which utilizes a custom designed circuit. 
The circuit contains BME280 Sensor and the PIR Motion Sensor. 
BME280 delivers statistics such as Temperature, Humidity , Pressure. 
PIR sensor detects motion. 

## Development Stages
____________________

Developed the User Interface design using Adobe Xd
Created an android application using Android Studio in Kotlin (Self Taught) 
Due to Covid Restrictions - one of my partners had the device (built on top of a raspberry pi and our custom PCB board ) with him. We were able to write a python script to get the information from BME280 sensor and the PIR motion sensor and displayed it to the terminal. 
We made the cloud connection between the device and Firebase Database and were able to send the information to the database which can be stored and retrieved. 
Able to set up Authentication using Google as the main login platform 
We then made the connection from the android applicaitona and Firebase so that our data( Temperature, Humidity, Pressure, Motion Sensor ) could be displayed in Realtime. 
Since this is a home monitoring system, if any motion was sensed a notifiation messaged was sent to the owner that motion was sensed. This was implemented by Firebase's Cloud Messaging platform. 

## Technologies Used: 
Kotlin, Android Studio, AdobeXD , Firebase Authentication, Firebase Realtime-Database, Firebase Firestore, Firebase Cloud Messaging, Python,Raspberry Pi, OpenWeather Weather API, Pyrebase 
##
A video demonstration on how the Iot project works , Please Check it out ! 
https://youtu.be/GDCdV11pFPg
