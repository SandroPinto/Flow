Feature: Email
 Scenario Outline: User sends a message to a Message Server
    Given A User sends a "<client message>" message
    When The message is converted by the Adapter
    Then The Message server should contain the "<server message>" message in the queue

   Examples:
   |client message| server message|
   |hello  |hello           |
   |bye    |bye             |
