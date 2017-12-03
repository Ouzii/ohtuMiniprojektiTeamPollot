Feature: User can add a tag to the system

    Scenario: User can add a tag to the system
        Given user is at the tags page
        When tag name "taginNimi" is entered
        Then "taginNimi" is shown

