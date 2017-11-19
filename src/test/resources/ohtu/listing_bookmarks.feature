Feature: User can inspect a list of their Tips

    Scenario: reading tips are successfully fetched from the database and presented to the user
        Given command listaa is selected
        Then all reading tips get printed to console