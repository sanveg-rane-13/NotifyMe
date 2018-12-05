# NotifyMe

Website to track product prices on various ecommerce websites.

Working:

Server Side:
* Handle user authentication - Spring Security
* Save user product interests and desired price ranges
* Check the website xpath and track product prices - Scheduled check every 5 minutes
* In case of product price match notify user - Email and SMS with a minified link to the product
* Track price changes of various products marked by user and recommend to other users

User Interface:
* User Registration / User Login forms
* Page to render the ecommerce website of user interest
* User track history page - provide details of user's products

