Feature: User can add Podcasts to tips

    Scenario: user can add a podcast when correct details are entered
        Given user is at the podcast page
        When valid name "nimi" valid url "http://www.google.com " valid artisti "artisti" and valid date "20.12.1992" are entered
        Then "http://www.google.com" is shown