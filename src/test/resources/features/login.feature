@wip
Feature: ABN AMRO WebPage Login feature

  Validate all users can log in with valid credentials and land on home page


  Background:
    Given user is on the login page


  Scenario Outline: AC-1 => User should be able to login with valid credentials
    When user enters "<user_email_address>" and "<password>" and logins
    Then user lands on the "<page_heading>"

    Examples: Valid credentials for users

      | user_email_address | password              | page_heading |
      | valid_user_email_1 | valid_user_password_1 | Home         |
      | valid_user_email_2 | valid_user_password_2 | Home         |
      | valid_user_email_3 | valid_user_password_3 | Home         |


  Scenario Outline: AC-1 => User shouldn't be able to login with invalid credentials

    When user enters invalid "<user_email_address>" or invalid "<password>" and clicks login button
    Then user doesn't land on the "<page_heading>"

    Examples: Invalid credentials for users
      | user_email_address     | password  | page_heading |
      | noneadmin@admin.com    | 2020      | Home         |
      | biancunha@gmail.com    | 123456AAA | Home         |
      | growdev@growdev.com.br | growdev   | Home         |


  Scenario Outline: AC-1 => System shouldn't allow users to access the application without providing credentials
  (for example, copy the URL after login, then log out,
  paste the same URL to the browser and try to skip the authentication step)

    When user enters "<user_email_address>" and "<password>" and logins
    And user copies the URL
    And user logouts
    And user pastes the URL to browser and enters login without authentication
    Then user is still on the login page

    Examples: Valid credentials for users
      | user_email_address | password              |
      | valid_user_email_1 | valid_user_password_1 |
      | valid_user_email_2 | valid_user_password_2 |
      | valid_user_email_3 | valid_user_password_3 |