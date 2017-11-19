Feature: A new reading tip can be added to the database

    Scenario: reading tip is successfully saved to database if name and type are valid
        Given command lisaa is selected
        When a valid name "Tekniikan Maailma 2017/2 sivu 3." and type "Lehti" are entered
        Then a new reading tip is added to the database
        