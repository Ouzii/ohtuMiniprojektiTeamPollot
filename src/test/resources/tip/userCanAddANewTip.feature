Feature: User can add a new tip to the system
    
    Scenario: User can add new tip to the system when correct details are given
        Given user is at the main page
        When valid name "nimi" and valid isbn "978-951-98548-9-2" and valid kirjoittaja "kirjoittaja" are entered
        Then tip with name "nimi" and isbn "978-951-98548-9-2" and kirjoittaja "kirjoittaja" is listed

    Scenario: User cant add a new tip if the isbn is not valid
        Given user is at the main page
        When valid name "nimi" and invalid isbn "9as22dasddas" and valid kirjoittaja "kirjoittaja" are entered
        Then tip with name "nimi" and isbn "978-951-98548-9-2" and kirjoittaja "kirjoittaja" is not listed