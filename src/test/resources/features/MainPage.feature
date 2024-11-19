# Test Case 1:
# HP_01 Verify Homepage Load and Content Display
# Test Steps:
# 1. Open the company's website (https://toqio.co/).
# 2. Verify that the page loads without errors (e.g., 404, 500).
# 3. Check that the company logo, main banner, and navigation menu are visible.
# 4. Ensure that the links in the navigation bar (e.g., "Home," "About Us," "Services") are clickable and lead to the correct pages.
# 5. Scroll down and check the footer content, ensuring links (e.g., Privacy Policy, Contact Us) are present and functional.

@regression_01
@QA_MSEN
@home
@HP_01
Feature: HP_01 Verify Homepage Load and Content Display

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
      | Rethink finance. Rediscover growth.               |
      | Thriving together                                 |
      | Launch smarter and faster with Toqio              |
      | What you can do with Toqio                        |
      | From Toqio to embedded finance: read our insights |
      | Toqio in the news                                 |
      | Latest awards                                     |

  Scenario Outline: HP_01 TC_02 verify correct pages open when clicked
#    Then user clicks the "<links>" in the navigation bar and verifies they lead to the correct "<pages>"
    When user clicks the Header Item Links "<links>"
    Then user verifies the directed URL "<pages>"
    Then user verifies the titles "<titles>" of the page
    Examples:
      | links     | pages                      | titles                                     |
      | Home      | https://toqio.co/          | Rethink finance. Rediscover growth.        |
      | Platform  | https://toqio.co/platform  | Build financial products your way, faster. |
      | Use Cases | https://toqio.co/use-cases | Use cases                                  |

  Scenario: HP_01 TC_03 verify the Company related links appear and functional
    When user hovers over the link Company
    Then user verifies the Company related links appear and functional
      | About Us |
      | Newsroom |
      | Talent   |
      | Team     |
      | Contact  |

  Scenario Outline: HP_01 TC_04 verify the Company related links appear
    When user hovers over the link Company
    When user clicks the Company Related Item Links "<links>"
    Then user verifies the directed URL "<pages>"
    Then user verifies the titles "<titles>" of the page
    Examples:
      | links    | pages                      | titles                                                            |
      | About Us | https://toqio.co/about-us/ | What is Toqio?                                                    |
      | Newsroom | https://toqio.co/newsroom  | Newsroom                                                          |
      | Team     | https://toqio.co/team      | Leadership                                                        |
      | Talent   | https://toqio.co/talent    | If you're forward-thinking about finance, this is the place to be |
      | Contact  | https://toqio.co/contact   | Contact us                                                        |

  Scenario Outline: HP_01 TC_05 verify the Resources related links appear and functional
    When user hovers over the link Resources
    Then user verifies the Resources related links appear and functional
      | Insights |
      | Podcast  |

    When user clicks the Resources Related Item Links "<links>"
    Then user verifies the directed URL "<pages>"
    Then user verifies the titles "<titles>" of the page
    Examples:
      | links          | pages                                                                                                        | titles                                                                   |
      | Insights       | https://toqio.co/insights                                                                                    | Insights                                                                 |
      | Podcast        | https://toqio.co/podcast                                                                                     | Embedded in the Market                                                   |
      | Reports/Guides | https://toqio.co/reports-guides                                                                              | Reports/Guides                                                           |
      | Free ebook!    | https://toqio.co/toqio-2024-q3-ebook-unlocking-growth-in-distribution-the-invisible-hand-of-embedded-finance | Unlocking growth in distribution: The invisible hand of embedded finance |


  Scenario: HP_01 TC_06 Verify the footer content links are present and functional.
    Then user verifies the company logo is visible in the footer
    Then user verifies the Copyright is visible in the footer
    Then user verifies the Social Media Links are visible in the footer
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

  Scenario Outline: HP_01 TC_07 verify Footer Item links directed correctly
    When user clicks the Footer Item Links "<links>"
    Then user verifies the directed URL "<pages>"
    Then user verifies the titles "<titles>" of the page
    Examples:
      | links          | pages                           | titles                                                            |
      | Home           | https://toqio.co/               | Rethink finance. Rediscover growth.                                                                  |
      | Platform       | https://toqio.co/platform       | Build financial products your way, faster.                                                |
      | Use Cases      | https://toqio.co/use-cases      | Use cases                                                         |
      | Insights       | https://toqio.co/insights       | Insights                                                          |
      | Podcast        | https://toqio.co/podcast        | Embedded in the Market The podcast hosted by Toqio!               |
      | About Us       | https://toqio.co/about-us/      | What is Toqio?                                                    |
      | Newsroom       | https://toqio.co/newsroom       | Newsroom                                                          |
      | Team           | https://toqio.co/team           | Leadership                                                        |
      | Talent         | https://toqio.co/talent         | If you're forward-thinking about finance, this is the place to be |
      | Contact        | https://toqio.co/contact        | Contact us                                                        |
      | Privacy policy | https://toqio.co/privacy-policy | Privacy policy                                                    |
      | Cookies        | https://toqio.co/cookies        | Cookie information                                                |
      | Legal notice   | https://toqio.co/legal-notice   | Legal notice                                                      |
      | Terms of use   | https://toqio.co/terms-of-use   | Terms of use                                                      |

  Scenario: HP_01 TC_08
    * user checks if the language is English
    Then user verifies language icon visible and functional
    And user hovers over the Language Icon
    Then user verifies English and Spanish language icons are visible and functional
    When user selects the Spanish Language option
    Then user verifies the URL of the page is "https://toqio.co/es/"
    Then user verifies the title "Potencia tu red de distribución para un rendimiento óptimo" appears
    And user hovers over the Language Icon
    * user checks if the language is English
    Then user verifies the button Connect Us is visible and functional
