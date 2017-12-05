Feature: User can edit podcasts
#    Lisätään testidataa ensin
    Scenario: User enters valid modification values
        
        Given user is at the podcast page
        And valid name "nimi" valid url "http://www.google.com " valid artisti "artisti" and valid date "20.12.1992" are entered
    
        And user is at the podcast modification page of "7"
        And modification details valid name "nimi" valid url "http://www.google.com/hehe" valid artisti "artisti" and valid date "20.12.1992" are entered
        Then "tip has succesfully been modified olalala" is shown