# Test Steps:
# 1. Navigate to the contact or subscription page on https://toqio.co/.
# 2. Fill out the required fields (e.g., name, email, message).
# 3. Click the submit button.
# 4. Verify that the form is submitted successfully (no validation errors or issues).
# 5. Check for any success messages (e.g., “Thank you for your submission”).
# 6. Confirm the data is correctly stored or processed by verifying it on the backend or through confirmation emails (if available).

Feature:


  Background: User lands on the Main Page
    Given user lands on the Main Page

  Scenario:
    When user clicks the button Request Demo
    Then user verifies the URL of the page is "https://toqio.co/contact-sales/"