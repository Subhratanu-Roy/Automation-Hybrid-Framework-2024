Feature: Product Catalogue page validation

@Regression
Scenario Outline:
Given User is on the home page
Then <product> is displayed on the page

Examples:
|product|
|ZARA COAT 3|