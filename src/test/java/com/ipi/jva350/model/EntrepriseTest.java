package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.LinkedHashSet;

public class EntrepriseTest {

    @Test
    void estDansPlageFalseAfter(){
        // Given
        Entreprise entreprise = new Entreprise();

        LocalDate now = LocalDate.of(2022, 3, 23);
        LocalDate dateDebut = LocalDate.of(2022, 3, 15);
        LocalDate dateFin = LocalDate.of(2022, 3, 17);

        //WHEN
        boolean res = entreprise.estDansPlage(now, dateDebut, dateFin);

        //THEN
        Assertions.assertEquals(false, res);
    }
    @Test
    void estDansPlageFalseBefore(){
        // Given
        Entreprise entreprise = new Entreprise();

        LocalDate now = LocalDate.of(2022, 3, 10);
        LocalDate dateDebut = LocalDate.of(2022, 3, 15);
        LocalDate dateFin = LocalDate.of(2022, 3, 17);

        //WHEN
        boolean res = entreprise.estDansPlage(now, dateDebut, dateFin);

        //THEN
        Assertions.assertEquals(false, res);
    }
    @Test
    void estDansPlageTrue(){
        // Given
        Entreprise entreprise = new Entreprise();

        LocalDate now = LocalDate.of(2022, 3, 16);
        LocalDate dateDebut = LocalDate.of(2022, 3, 15);
        LocalDate dateFin = LocalDate.of(2022, 3, 17);

        //WHEN
        boolean res = entreprise.estDansPlage(now, dateDebut, dateFin);

        //THEN
        Assertions.assertEquals(true, res);
    }

    @Test
    void estDansPlageLimiteDebut(){
        // Given
        Entreprise entreprise = new Entreprise();

        LocalDate now = LocalDate.of(2022, 3, 15);
        LocalDate dateDebut = LocalDate.of(2022, 3, 15);
        LocalDate dateFin = LocalDate.of(2022, 3, 17);

        //WHEN
        boolean res = entreprise.estDansPlage(now, dateDebut, dateFin);

        //THEN
        Assertions.assertEquals(true, res);
    }

    @Test
    void estDansPlageLimiteFin(){
        // Given
        Entreprise entreprise = new Entreprise();

        LocalDate now = LocalDate.of(2022, 3, 17);
        LocalDate dateDebut = LocalDate.of(2022, 3, 15);
        LocalDate dateFin = LocalDate.of(2022, 3, 17);

        //WHEN
        boolean res = entreprise.estDansPlage(now, dateDebut, dateFin);

        //THEN
        Assertions.assertEquals(true, res);
    }
//Avec la méthde de travail TDD on peut mettre dans la fonction première de l'algorithme la vérification des dates aux limites de la période


    @ParameterizedTest(name = "{0} est ferie")
    @CsvSource({
            "'2022-12-25'",
            "'2022-07-14'",
            "'2022-01-01'"
    })
    void estJourFerieTrue(String debut){
        //Given
        Entreprise entreprise = new Entreprise();
        LocalDate date = LocalDate.parse(debut);

        //When
        boolean res = entreprise.estJourFerie(date);

        //Then
        Assertions.assertEquals(true, res);
    }

    @ParameterizedTest(name = "{0} est pas ferie")
    @CsvSource({
            "'2022-12-24'",
            "'2022-12-26'",
            "'2022-07-13'",
            "'2022-07-15'",
            "'2022-12-31'",
            "'2022-01-02'",
            "'2022-04-21'"
    })
    void estJourFerieFalse(String debut){
        //Given
        Entreprise entreprise = new Entreprise();
        LocalDate date = LocalDate.parse(debut);

        //When
        boolean res = entreprise.estJourFerie(date);

        //Then
        Assertions.assertEquals(false, res);
    }

    @Test
    void getPremierJourAnneeDeCongesNull(){
        // Given
        Entreprise entreprise = new Entreprise();

        LocalDate now = null;

        //WHEN
        LocalDate res = entreprise.getPremierJourAnneeDeConges(now);

        //THEN
        Assertions.assertEquals(null, res);
    }
    @Test
    void getPremierJourAnneeDeCongesMountSup5(){
        // Given
        Entreprise entreprise = new Entreprise();

        LocalDate now = LocalDate.of(2022, 6, 15);

        //WHEN
        LocalDate res = entreprise.getPremierJourAnneeDeConges(now);

        //THEN
        Assertions.assertEquals(LocalDate.of(2022, 06, 01), res);
    }
    @Test
    void getPremierJourAnneeDeCongesMountLess5(){
        // Given
        Entreprise entreprise = new Entreprise();

        LocalDate now = LocalDate.of(2022, 3, 15);

        //WHEN
        LocalDate res = entreprise.getPremierJourAnneeDeConges(now);

        //THEN
        Assertions.assertEquals(LocalDate.of(2021, 06, 01), res);
    }
    @Test
    void getPremierJourAnneeDeCongesMountLess5LimitInf(){
        // Given
        Entreprise entreprise = new Entreprise();

        LocalDate now = LocalDate.of(2022, 4, 30);

        //WHEN
        LocalDate res = entreprise.getPremierJourAnneeDeConges(now);

        //THEN
        Assertions.assertEquals(LocalDate.of(2021, 06, 01), res);
    }
    @Test
    void getPremierJourAnneeDeCongesMountSup5LimitSup(){
        // Given
        Entreprise entreprise = new Entreprise();

        LocalDate now = LocalDate.of(2022, 6, 30);

        //WHEN
        LocalDate res = entreprise.getPremierJourAnneeDeConges(now);

        //THEN
        Assertions.assertEquals(LocalDate.of(2022, 06, 01), res);
    }
}
