Feature: Managing books
  Background:
    * url urlBase

  Scenario:
    Given path 'libraries'
    * set libraryRequest
      | path    | value                                                     |
      | name    | "Library with books"                                      |
      | address | {"addressLine1":"7 rue du superman",  "zipCode": "91200"} |
      | contact | {"firstname": "Clark", "lastname": "Kent"}                |
    And request libraryRequest
    When method POST
    Then status 201
    * def libId = response.id

    Given path 'libraries', libId, 'books'
    When method GET
    Then status 200
    And match response == {"books": []}

    Given path 'libraries', libId, 'books'
    * set bookRequest1
      | path  | value                                  |
      | title | "Archive de Roshar - la voie des rois" |
      | isbn  | "9782253191230"                        |
    And request bookRequest1
    When method POST
    Then status 201
    * def book1 = { "title": "Archive de Roshar - la voie des rois", "isbn": "9782253191230"}
    And match response == book1

    Given path 'libraries', libId, 'books'
    * set bookRequest2
      | path  | value                                     |
      | title | "Magi - The Labyrinth of Magic - tome 02" |
      | isbn  | "9782823842364, 2823842365"               |
    And request bookRequest2
    When method POST
    Then status 201
    * def book2 = { "title": "Magi - The Labyrinth of Magic - tome 02", "isbn": "9782823842364, 2823842365"}
    And match response == book2

    Given path 'libraries', libId, 'books'
    When method GET
    Then status 200
    And match response.books contains book1
    And match response.books contains book2