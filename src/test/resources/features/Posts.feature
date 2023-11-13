Feature: User is able to create, update and delete posts

  Scenario Outline: A post is created with valid title, body and userId.
    Given there is an user has "<username>" username
    When "<title>" title is entered for the post
    And "<body>" body is entered for the post
    And test userId is entered for the post
    And POST request is send with the post
    Then post is created successfully
    Examples:
      | title | body | username  |
      | foo   | bar  | Antonette |


  Scenario Outline: User can update already created post.
    Given there is an user has "<username>" username
    And there is a post written by test user
    When user updates post "<title>" title
    And user updates post "<body>" body
    And user sets id for the post
    And test user id is updated for the post
    And PATCH request is sent for the post
    Then post updated successfully
    Examples:
      | title | body | username  |
      | foo   | bar  | Antonette |

  Scenario Outline: User can delete already created post.
    Given there is an user has "<username>" username
    And there is a post written by test user
    When user sets id for the post
    And DELETE request is sent for the post
    Then post deleted successfully
    Examples:
      | username  |
      | Antonette |

