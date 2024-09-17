# CS_01 user fills the Contact us now form
# Test Steps:
# 1. Navigate to the contact or subscription page on https://toqio.co/.
# 2. Fill out the required fields (e.g., name, email, message).
# 3. Click the submit button.
# 4. Verify that the form is submitted successfully (no validation errors or issues).
# 5. Check for any success messages (e.g., “Thank you for your submission”).
# 6. Confirm the data is correctly stored or processed by verifying it on the backend or through confirmation emails (if available).

@smoke_01
@QA_MSEN
@contact_sales
@CS_01
Feature: CS_01 user fills the Contact us now form

  Background: User lands on the Main Page
    Given user lands on the Main Page
    When user clicks the button Request Demo
    Then user verifies the URL of the new tab is "https://toqio.co/contact-sales/"

  Scenario: CS_01 TC_01
    Then user verifies the title Contact us now! appears
    Then user verifies the Contact us inboxes and checkboxes are functional

  @bug
  Scenario: CS_01 TC_02
  // TODO: There is a bug on Email inbox. It doesn't accept any kinds of email
    And user fills the First name inbox
    And user fills the Last name inbox
    And user fills the Email inbox
    And user fills the Phone number inbox
    And user fills the Company name inbox
    And user fills the Country_Region inbox
    And user selects a country from the Headquartered in inbox
    And user fills the How did you hear about us? inbox
    And user checks the Agreement checkbox
    And user checks the subscription checkbox
    And user clicks the button Send

  Scenario: CS_01 TC_02
    And user clicks the button Send
    Then user verifies the Error Messages "Please complete this required field." on the required fields

