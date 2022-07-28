Feature: Hello World API

  Scenario Outline: Check first name
    Given I have a first name of "<firstName>"
    When I call the following API "http://localhost:8080/hello?firstName=<firstName>" with a GET
    Then I receive a 200 HTTP status
    And I have the following values in the response
    |firstName|<firstName> |
    |lastName |null |

    Examples:
    |firstName|
    |Jack         |
    |Stacy             |
    |Carol             |