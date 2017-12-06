Feature: User can remove a podcast
    
    Scenario: User removes an existing podcast
        Given user is at the main page
        When delete button is clicked
        Then one item has been deleted