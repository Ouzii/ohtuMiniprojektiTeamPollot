Feature:  User can add and remove tags from a tip
    

#    Lisätään myös varmuuden vuoksi testitagi ja testikirjat
    Scenario: user can add a tag to a tip
        Given user is at the tags page
        When tag name "testiTagi" is entered

        And testbooks are added
        And user is at the modification page of "1"
        And wait a second

        And tag "testiTagi" is added to the tip
        And wait a second
        Then tip contains tag "testiTagi"

    Scenario: user can delete a tag from a tip
        Given user is at the modification page of "1"
        And tag "testiTagi" is removed from the tip
        Then tip does not contain tag "testiTagi"