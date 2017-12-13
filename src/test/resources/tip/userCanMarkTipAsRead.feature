Feature: User can mark book as read

    Scenario: user can mark book as read
        Given user is at the modification page of "1"
        When user checks the checkbox
        And user is at the modification page of "1"
        Then the checkbox is checked

    Scenario: user can mark book as unread
        Given user is at the modification page of "1"
        When user unchecks the checkbox
        And user is at the modification page of "1"
        Then the checkbox is unchecked