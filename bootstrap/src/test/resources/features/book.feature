Feature: Managing books
  Background:
    * url urlBase

  Scenario: Add books to libraries
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
      | path        | value                                  |
      | displayName | "Archive de Roshar - la voie des rois" |
      | isbn        | "9782253191230"                        |
    And request bookRequest1
    When method POST
    Then status 201
    * set book1
      | path              | value                                  |
      | displayName       | "Archive de Roshar - la voie des rois" |
      | title             | "La Voie des rois"                     |
      | isbn              | "9782253191230"                        |
      | authors           | ["Brandon Sanderson"]                  |
      | publisher         | "Livre de Poche"                       |
      | publishedDate     | "2015-05-20"                           |
      | description       | "#string"                              |
      | googlePreviewLink | "#string"                              |
    And match response == book1

    Given path 'libraries', libId, 'books'
    * set bookRequest2
      | path        | value            |
      | displayName | "Magi tome 02" |
      | isbn        | "9782823842364"  |
    And request bookRequest2
    When method POST
    Then status 201
    * set book2
      | path              | value                                     |
      | displayName       | "Magi tome 02"                            |
      | title             | "Magi - The Labyrinth of Magic - tome 02" |
      | isbn              | "9782823842364"                           |
      | authors           | ["Shinobu Ohtaka"]                        |
      | publisher         | "12-21"                                   |
      | publishedDate     | "2015-06-23"                              |
      | description       | "#string"                                 |
      | googlePreviewLink | "#string"                                 |
    And match response == book2

    Given path 'libraries', libId, 'books'
    When method GET
    Then status 200
    And match response.books contains book1
    And match response.books contains book2