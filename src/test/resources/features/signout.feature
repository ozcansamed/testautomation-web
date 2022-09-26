Feature: ABN AMRO WebPage Sign Out feature

  As a user I should be able to sign out


  Background:
    Given user is on the home page with entering "valid_user_email_2" and "valid_user_password_2".

  @smoke
  Scenario: AC-1 => User can sign out by using sign out button inside profile info and ends up in login page.

    When user clicks sign out button.
    Then user should be able to sign out and lands on the login page

  @smoke
  Scenario: AC-2 => User can not go to the home page again by clicking the step back button after successfully signed out.

    When user clicks sign out button.
    And clicks step back button
    Then user shouldn't be able to land on home page


  Scenario: AC-3 => The user must be signed out if the user is away from keyboard for 1 minute (if the user does not do any  mouse or keyboard action for 3 minutes)

    When user is away from keyboard for 1 minute
    Then user must be signed out










