@login @wip
Feature: ABN AMRO WebPage Login feature

  Validate all users can log in with valid credentials and land on home page


  Background:
    Given user is on the login page

  @smoke
  Scenario Outline: AC-1 => User should be able to login with valid credentials
    When user enters "<user_email_address>" and "<password>" and logins
    Then user lands on the "<page_heading>"

    Examples: Valid credentials for users

      | user_email_address | password              | page_heading |
      | valid_user_email_1 | valid_user_password_1 | Home         |
      | valid_user_email_2 | valid_user_password_2 | Home         |
      | valid_user_email_3 | valid_user_password_3 | Home         |

  @wip
  Scenario Outline: AC-1 => Negative Scenario => User shouldn't be able to login with invalid credentials

    When user enters invalid "<user_email_address>" or invalid "<password>" and clicks login button
    Then user cannot login and still on Login Page

    Examples: Invalid credentials for users
      | user_email_address     | password  |
      | noneadmin@admin.com    | 2020      |
      | biancunha@gmail.com    | 123456AAA |
      | growdev@growdev.com.br | growdev   |


  Scenario Outline: AC-1 => Negative Scenario => System shouldn't allow users to access the application without providing credentials
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

  @regression
  Scenario Outline: AC-2 => "Please fill out this field." message should be displayed for any empty field

    When user enters only username "<user_email_address>" and tries to login
    Then user gets "<expected_validationMessage>" for password
    And user is on the login page
    When user enters only password "<password>" and tries to login
    Then user gets "<expected_validationMessage>" for username

    Examples: Invalid credentials and warning message:
      | user_email_address | password | expected_validationMessage |
      |                    | 2020     | Please fill in this field. |
      | admin@admin.com    |          | Please fill in this field. |

  @smoke
  Scenario: AC-3 => Validate the Password text entered into the 'Password' field is toggled to hide its visibility

    Then password field is toggled to hide

  @smoke
  Scenario Outline: AC-4 => User "E-mail address" field must contain a '@' character.

    Then "<user_email_address>" must contain a "@" character

    Examples: Valid credentials for users
      | user_email_address |
      | valid_user_email_1 |
      | valid_user_email_2 |
      | valid_user_email_3 |


  Scenario: AC-5 => Validate user sees placeholder in user e-mail input field.

    Then User e-mail input field contains "E-mail address" as a placeholder

  Scenario: AC-5 => Validate user sees placeholder in password input field.

    Then User password field contains "Password" as a placeholder

  @smoke
  Scenario: AC-6 => Validate user sees "Single Page Application" as the title.

    Then Page title is "Single Page Application"

  @smoke
  Scenario: AC-7 => Validate in the login page, user sees given text above credentials area.

    Then In login page, user sees "Automation doesn't stop at testing, it's just a beginning!"

  @smoke
  Scenario: AC-8 => Validate in the login page, user sees given text in footer.

    Then Footer is "Thank you for participating!"


  Scenario: AC-9 => Validate in the login page, user sees ABN AMRO image as the background image.

    Then Background image is "/img/bg1.jpg"

  Scenario: AC-10 => Validate in the login page, user sees "#3E3F41" as background-color.

    Then Background color is "#3E3F41"

  @smoke
  Scenario: AC-11 => Validate system shouldn't allow users to copy the text entered into the Password field
    When User enters valid credentials to password input box
    Then the system should not allow user to copy password


  Scenario: AC-12 => Password is not visible in the Page Source
    When User enters valid credentials to password input box
    Then the password is not visible in the Page Source

