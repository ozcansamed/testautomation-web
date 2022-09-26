Feature: ABN AMRO WebPage Home Page feature

  As a user I should be able to use the home page functionalities.


  Background:
    Given user is on the home page with entering "valid_user_email_1" and "valid_user_password_1"

  @smoke
  Scenario: AC-1 => User should see “Products” header on the home page

    Then user should see "Products" header

  @smoke
  Scenario: AC-2 => Profile icon must be clickable.

    When User clicks profile icon
    Then user sees Sign Out button

  Scenario: AC-3 => user should see a text which contains “Lorem ipsum”

    Then Content contains "Lorem ipsum"


  Scenario: AC-4 => Validate in the sign out page, user sees given text in footer.

    Then On the home page, footer is "Thank you for participating!"

  Scenario: AC-5 => Validate user sees "Single Page Application" as the title.

    Then On the home page, page title is "Single Page Application"

  Scenario: AC-6 => Validate on the home page, user sees ABN AMRO logo as a background image.

    Then On the home page, background image is "http://localhost:3001/img/bg1.jpg"
