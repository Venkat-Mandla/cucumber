@IUP
Feature: Login
	Scenario: With valid credentials
		Given User entered valid userName or phoneNumber as "XXXXXXXXX@gmai.com" and password as "XXXXX"
		When click on loginbutton "loginbutton" button
		Then render home page

	