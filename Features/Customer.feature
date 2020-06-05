Feature: Custsomer

Background: The following are the common steps for all scenarios
	Given User Launch Chrome Browser
	When User opens URL "https://admin-demo.nopcommerce.com/login"
	And User enters Email as "admin@yourstore.com" and Password as "admin"
	And Click on Login
	Then user can view Dashboard

@sanity
Scenario: Add New Customer
	When User click on customers Menu
	And click on customers menu item
	And click on Add New Button
	Then User can view Add New Customer Page
	When User enters customer info
	And click on save button
	Then user can view confirmation message "The new customer has been added successfully."
	And Close browser

@regression	
Scenario: Search customer by Email
	When User click on customers Menu
	And click on customers menu item
	And Enter customer Email
	When Click on search button
	Then User should find Email in the search table
	And Close browser

@regression
Scenario: Search customer by Name
	When User click on customers Menu
	And click on customers menu item
	And Enter customer FirstName
	And Enter customer LastName
	When Click on search button
	Then User should find Name in the search table
	And Close browser