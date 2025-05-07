Feature: Managing a library
  Background:
    * url urlBase

  Scenario: Get list of library
    Given path 'libraries'
    When method GET
    Then status 200
    And match response == {"libraries": []}

    Given path 'libraries'
    * set libraryRequest1
      | path    | value                                                                                                                            |
      | name    | "My personal library"                                                                                                            |
      | address | {"addressLine1":"7 rue du gentleman", "addressLine2":"lieu-dit merveilleux", "addressLine3":"Appartement B", "zipCode": "774560"} |
      | contact | {"firstname": "Arthur", "lastname": "Pendragon"}                                                                                  |
    And request libraryRequest1
    When method POST
    Then status 201
    * def lib1 = {"id": "#string", "name": "My personal library" , "address": {"addressLine1":"7 rue du gentleman", "addressLine2":"lieu-dit merveilleux", "addressLine3":"Appartement B", "zipCode": "774560"}, "contact":  {"firstname": "Arthur", "lastname": "Pendragon" }}
    And match response == lib1
    * set lib1.id = response.id

    Given path 'libraries'
    When method GET
    Then status 200
    And match response.libraries contains lib1

    Given path 'libraries'
    * set libraryRequest2
      | path    | value                                                                                                                            |
      | name    | "My second library"                                                                                                            |
      | address | {"addressLine1":"8 rue du gentleman", "zipCode": "89200"} |
    And request libraryRequest2
    When method POST
    Then status 201
    * def lib2 = {"id": "#string", "name": "My second library" , "address": {"addressLine1":"8 rue du gentleman", "zipCode": "89200"}, "contact":{} }
    And match response == lib2
    * set lib2.id = response.id

    Given path 'libraries'
    When method GET
    Then status 200
    And match response.libraries contains lib1
    And match response.libraries contains lib2