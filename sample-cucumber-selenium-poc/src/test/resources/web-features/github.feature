Feature: Open Web Page
Scenario: With valid url

Given User entered valid url as "http://github.com/venkat-mandla/camel"
When pressed enter button
Then render home page

	