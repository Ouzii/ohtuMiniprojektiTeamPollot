# ohtuMiniprojektiTeamPollot

Tämä on Helsingin yliopiston syyslukauden 2017 ohjelmistotuotanto-kurssiin liittyvä osatehtävä/harjoitus, jossa tehtävänä on tuottaa ketterin menetelmin (scrum) ohjelmisto neljän viikon aikana. Tarkoitus on tuottaa [lukuvinkkikirjasto](https://github.com/mluukkai/ohjelmistotuotanto2017/wiki/miniprojekti-speksi) neljässä sprintissä. Asiakastapaamisia (=ohjaaja) on kolme(?), joissa tarkoitus on pyrkiä selvittämään mitä oikein halutaan ja sitten pyrkiä toteuttamaan niistä tärkeimmät ominaisuudet seuraavassa sprintissä.

## Backlog

https://drive.google.com/open?id=16cQePEa3Y4ZwrMPcriLoQ8XcfTCgFnOQ

## Muuta informaatiota

[Käytössä oleva spring-cucumber pohja/konfiguraatio](https://github.com/mluukkai/spring-cucumber)

## Huom.

Nyt jotenkuten pystyy valitsemaan ajetaanko tomikissaa vai cli:

Ilman webiserveriä (build.gradle kertoo, että valitaan ohtucli.App mluukkain esimerkki 3viikon tehtävistä).

gradle run 

ja jos haluaa webosaston käyntiin joka käynnistää ohtu.Main:n

gradle run -Pweb=1 


### Travisin status:
[![Build Status](https://travis-ci.org/Ouzii/ohtuMiniprojektiTeamPollot.svg?branch=master)](https://travis-ci.org/Ouzii/ohtuMiniprojektiTeamPollot)



### Definition of Done:
- Ominaisuus on implementoitu ja integroitu sovellukseen.
- Ominaisuudelle on tehty JUnit testit ja Cucumber testit.
- Travis-ci on buildannut onnistuneesti ominaisuuden kanssa.
- Ominaisuuden on hyväksynyt tekijän lisäksi vähintään yksi toinen ryhmän jäsen.


ISBBBBBBBBBBBBBBBBBBBBBN
private boolean validateISBN() {
        return isbn.matches("^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$|(?=(?:[0-9]+[- ])"
                + "{4})[- 0-9]{17}$)97[89][- ]?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]"
                + "+[- ]?[0-9]$");
    }
