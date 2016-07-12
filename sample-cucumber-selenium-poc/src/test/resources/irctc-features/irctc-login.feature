Feature: Login
	@UC
	Scenario: With valid url
		Given User entered valid irctc url as "https://www.irctc.co.in/eticketing/loginHome.jsf"
		Then render home page
	
	
	Scenario: With valid credentials
		Given User entered valid userName or phoneNumber as "mandla2218" and password as "ammanana."
		When click on loginbutton "loginbutton" button
		Then render home page

	