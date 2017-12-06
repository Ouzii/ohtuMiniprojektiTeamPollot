Feature: User can modify tips details name, isbn and writer

    Scenario: user can modify details of a tip that is present

    Scenario: user cannot modify details of a tip if incorrect isbn is entered
        Given user is at the modification page of "3"
        When user sets isbn to "asdasd"
        Then error "ISBN on vääränlainen" is shown

    Scenario: user cannot make the name longer than 63 characters
        Given user is at the modification page of "3"
        When user sets name to ""
        Then error "nimen pitää olla 1-63 merkkiä pitkä" is shown