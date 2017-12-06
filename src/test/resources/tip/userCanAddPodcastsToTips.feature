Feature: User can add Podcasts to tips

    Scenario: user can add a podcast when correct details are entered
        Given user is at the podcast page
        When valid name "Oracle Database PodCasts" valid url "https://player.fm/series/oracle-database-podcasts" valid artisti "artisti" and valid date "20.12.1992" are entered
        Then "https://player.fm/series/oracle-database-podcasts" is shown