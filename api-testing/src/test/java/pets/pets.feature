Feature: Petstore

Scenario: Get Pet, not authorized

Given url baseUrl + '/pets/1'
When method get
Then status 403

Given url baseUrl + '/pets/1?user_key=' + userKey
When method get
Then status 201
