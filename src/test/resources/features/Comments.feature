Feature: User is able to leave comments, update and delete

  Scenario Outline: Comment was created by user for a existing post
    Given there is an user has "<username>" username
    And there is a post written by test user
    And "<name>" name is set for comment
    And "<email>" email is set for comment
    And "<body>" body is set for comment
    And postId is set for new comment
    When POST request is send with the comment
    Then comment is created successfully
    Examples:
      | username  | name | email       | body |
      | Antonette | foo  | foo@foo.bar | bar  |

    Scenario Outline: user is able to create post and leave a comment under
      Given there is an user has "<username>" username
      When "<title>" title is entered for the post
      And "<body>" body is entered for the post
      And test userId is entered for the post
      And POST request is send with the post
      And "<name>" name is set for comment
      And "<email>" email is set for comment
      And "<body>" body is set for comment
      And postId is set for comment for existing post
      And POST request is send with the comment
      Then post and comment are created successfully
      Examples:
        | username  | name | email       | body | title     |
        | Antonette | foo  | foo@foo.bar | bar  | foo title |

  Scenario Outline: User can update already created comment.
    Given there is an user has "<username>" username
    And there is a post written by test user
    And all comments are fetched from CommentsAPI perspective for all post
    When "<name>" name is set for comment
    And "<email>" email is set for comment
    And "<body>" body is set for comment
    And comment id is set for existing comment
    And postId is set for existing comment
    And PATCH request is send with the comment
    Then comment is updated successfully
    Examples:
      | username | name | email       | body |
      | Antonette | foo  | foo@foo.bar | bar  |

  Scenario Outline: Comments written by single user can be fetched from both Comment and Post perspective
    Given there is an user has "<username>" username
    And there is a post written by test user
    When all comments are fetched from PostsAPI perspective for all post
    And all comments are fetched from CommentsAPI perspective for all post
    Then comments fetched from PostAPI and CommentApi are the same
    Examples:
      | username  |
      | Antonette |

  Scenario Outline: Post and Comments written by single user have same postId
    Given there is an user has "<username>" username
    And there is a post written by test user
    When all comments are fetched from CommentsAPI perspective for all post
    Then all postId values are same and matched with the post in the comments
    Examples:
      | username  |
      | Antonette |

  Scenario Outline: User can delete already created comment.
    Given there is an user has "<username>" username
    And there is a post written by test user
    And all comments are fetched from CommentsAPI perspective for all post
    And DELETE request is send with the comment
    Then comment is deleted successfully
    Examples:
      | username  |
      | Antonette |