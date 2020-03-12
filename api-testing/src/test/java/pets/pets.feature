Feature: Petstore

Scenario: Get Pet

Given url baseUrl + '/pets/1'
When method get
Then status 201
And match response == { id: '#notnull' }
