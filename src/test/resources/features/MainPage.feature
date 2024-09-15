# Test Case 1: Verify Homepage Load and Content Display
# 1. Open the company's website (https://toqio.co/).
# 2. Verify that the page loads without errors (e.g., 404, 500).
# 3. Check that the company logo, main banner, and navigation menu are visible.
# 4. Ensure that the links in the navigation bar (e.g., "Home," "About Us," "Services") are clickable and lead to the correct pages.
# 5. Scroll down and check the footer content, ensuring links (e.g., Privacy Policy, Contact Us) are present and functional.
# 6. Confirm that dynamic content, if any (such as testimonials or featured products/services), loads and displays as expected.

Feature: Verify Homepage Load and Content Display

  Background: User lands on the Main Page
    Given user lands on the Main Page

  Scenario: HP_01 TC_01 Verify Homepage Load and Content Display
    Then user verifies the URL of the page is "https://toqio.co/"
    Then user verifies the company logo is visible
    Then user verifies the main banner is visible
    Then user verifies the navigation menu is visible and functional
      | Home      |
      | Platform  |
      | Use Cases |
      | Company   |
      | Resources |

    Then user verifies the content titles are visible on Main page
      | Empower your distribution network for optimal performance                         |
      | Build a financial ecosystem on a single platform where everybody wins             |
      | How it works                                                                      |
      | Launch faster with a fully configurable platform that's easy to change and manage |
      | The Toqio platform as your growth accelerator                                     |
      | Everything you need in one place                                                  |
      | Our solutions                                                                     |
      | Trusted by                                                                        |
      | What we solve                                                                     |
      | Key industry examples                                                             |
      | Latest awards                                                                     |
      | Toqio in the news                                                                 |

  Scenario: HP_01 TC_02
    Then user clicks the links in the navigation bar and verifies they lead to the correct pages
      | Home      | https://toqio.co/          |
      | Platform  | https://toqio.co/platform  |
      | Use Cases | https://toqio.co/use-cases |

  Scenario Outline:
    Then user clicks the "<links>" in the navigation bar and verifies they lead to the correct "<pages>"
    Examples:
      | links     | pages                      |
      | Home      | https://toqio.co/          |
      | Platform  | https://toqio.co/platform  |
      | Use Cases | https://toqio.co/use-cases |

  Scenario:
  | Company   |
  | Resources |

  Scenario: HP_01 TC_03 Verify the footer content links are present and functional.
    Then user verifies the footer content links are present and functional
      | Home           |
      | Platform       |
      | Use Cases      |
      | Insights       |
      | Podcast        |
      | About Us       |
      | Newsroom       |
      | Team           |
      | Talent         |
      | Contact        |
      | Privacy policy |
      | Cookies        |
      | Legal notice   |
      | Terms of use   |

    When user clicks the Footer Items link and verifies the expected URL
      | Home           |
      | Platform       |
      | Use Cases      |
      | Insights       |
      | Podcast        |
      | About Us       |
      | Newsroom       |
      | Team           |
      | Talent         |
      | Contact        |
      | Privacy policy |
      | Cookies        |
      | Legal notice   |
      | Terms of use   |


  Scenario: HP_01 TC_04
#  6. Confirm that dynamic content, if any (such as testimonials or featured products/services), loads and displays as expected.
