Feature: Managing a library
    Background:
        * url urlBase
        * def libraryFormat = karate.read('classpath:features/json/library-format.json')

    Scenario: Get list of library
        Given path 'libraries'
        When method GET
        Then status 200
        And match response == '##[] #(libraryFormat)'