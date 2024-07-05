Feature: Purchase order from ECommerce


@Sanity
Scenario Outline: Positive test of purchasing an order
Given User is on the home page
When User adds product <productName> to cart 
And Checkout <productName> and select country <country> and click on place order
Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page
Examples:
	|username 					|			password	| productName  	|		country		|
	|subhratanu15@gmail.com		|			12345		| ZARA COAT 3 	|		India		|

