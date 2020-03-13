Feature: Petstore

Scenario: Get all pets, not authorized

Given url baseUrl + '/api/pets'
When method get
Then status 403

Scenario: Get all pets, authorized

Given url baseUrl + '/api/pets'
And header api-key = apiKey
When method get
Then status 200

Scenario: Create pets

Given url baseUrl + '/api/pets'
And header api-key = apiKey
And request {id: 1, name: 'Cat'}
When method post
Then status 200

Given url baseUrl + '/api/pets'
And header api-key = apiKey
And request {id: 2, name: 'Dogs'}
When method post
Then status 200

Given url baseUrl + '/api/pets'
And header api-key = apiKey
And request {id: 3, name: 'Guinea pig'}
When method post
Then status 200

Given url baseUrl + '/api/pets'
And header api-key = apiKey
And request {id: 4, name: 'Rabbit'}
When method post
Then status 200

Given url baseUrl + '/api/pets'
And header api-key = apiKey
And request {id: 5, name: 'Snake'}
When method post
Then status 200

Scenario: Delete pet

Given url baseUrl + '/api/pets/5'
And header api-key = apiKey
When method delete
Then status 200
