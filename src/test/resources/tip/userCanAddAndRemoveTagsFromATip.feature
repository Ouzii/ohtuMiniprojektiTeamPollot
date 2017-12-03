Feature:  User can add and remove tags from a tip
    
    Scenario: user can add a tag to a tip
        Given user is at the tags page
        When tag name "tagi1" is entered

        And user is at the modification page of "1"
        And tag "taginNimi" is added to the tip
        And user is at the modification page
        Then tip contains tag "taginNimi"