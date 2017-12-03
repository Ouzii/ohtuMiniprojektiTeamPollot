Feature: User can delete an existing tag

    Scenario: User can delete a tag
        Given user is at the tags page
        When tags delete button is pressed
        Then System will not show the deleted tag