Feature: Login

@sanity
Scenario: Successful Login with Valid Credentials
	Given 	User Launch Chrome Browser
	When 	User opens URL "https://admin-demo.nopcommerce.com/login"
	And 	User enters Email as "admin@yourstore.com" and Password as "admin"
	And 	Click on Login
	Then 	Page Title Should be "Dashboard / nopCommerce administration"
	When 	User Click on Log out link
	Then 	Page Title Should be "Your store. Login"
	And 	Close browser
	
@regression	
Scenario Outline: Login Data Driven
	Given 	User Launch Chrome Browser
	When 	User opens URL "https://admin-demo.nopcommerce.com/login"
	And 	User enters Email as "<email>" and Password as "<password>"
	And 	Click on Login
	Then 	Page Title Should be "Dashboard / nopCommerce administration"
	When 	User Click on Log out link
	Then 	Page Title Should be "Your store. Login"
	And 	Close browser
	
	Examples:
	| email | password |
	| admin@yourstore.com | admin |
	| admin@yourstore.com | admin |
	