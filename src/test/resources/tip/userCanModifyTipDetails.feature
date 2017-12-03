Feature: User can modify tips details name, isbn and writer

    Scenario: user can modify details of a tip that is present
        Given user is at the modification page
        When valid name "name" valid isbn "978-951-98548-9-2" and valid writer "kirjoittaja" and date "01.12.2016" are entered
        Then "tip has succesfully been modified olalala" is shown

    Scenario: user cannot modify details of a tip if incorrect isbn is entered
        Given user is at the modification page
        When valid name "name" invalid isbn "asdasd" and valid writer "kirjoittaja" and date "01.12.2016" are entered
        Then "ISBN ei ole muodossa ISBN13!" is shown

    Scenario: user cannot modify details of a tip if incorrect name is entered
        Given user is at the modification page
        When invalid name "" valid isbn "978-951-98548-9-5" and valid writer "kirjoittaja" and date "01.12.2016" are entered
        Then "nimimerkin nimi ei saa olla tyhjä" is shown

