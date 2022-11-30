//Pair programming Vincent Evieux, Félix Laterrot
package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.LinkedHashSet;

public class SalarieAideADomicileTest {

    @Test
    void aLegalementDroitADesCongesPayesNonInitialise(){
        // Given
        SalarieAideADomicile aide = new SalarieAideADomicile();

        //WHEN
        boolean res = aide.aLegalementDroitADesCongesPayes();

        //THEN
        Assertions.assertEquals(false, res);
    }

    @Test
    void aLegalementDroitADesCongesPayesMoinsDeDix(){
        // Given
        SalarieAideADomicile aide = new SalarieAideADomicile("Jack", LocalDate.now(), LocalDate.now(), 0, 0, 5, 1, 0);

        //WHEN
        boolean res = aide.aLegalementDroitADesCongesPayes();

        //THEN
        Assertions.assertEquals(false, res);
    }

    @Test
    void aLegalementDroitADesCongesPayesPlusDeDix(){
        // Given
        SalarieAideADomicile aide = new SalarieAideADomicile("Jack", LocalDate.now(), LocalDate.now(), 0, 0, 15, 1, 2);

        //WHEN
        boolean res = aide.aLegalementDroitADesCongesPayes();

        //THEN
        Assertions.assertEquals(true, res);
    }

    @Test
    void aLegalementDroitADesCongesPayesAuxLimite9(){
        // Given
        SalarieAideADomicile aide = new SalarieAideADomicile("Jack", LocalDate.now(), LocalDate.now(), 0, 0, 9, 1, 2);

        //WHEN
        boolean res = aide.aLegalementDroitADesCongesPayes();

        //THEN
        Assertions.assertEquals(false, res);
    }

    @Test
    void aLegalementDroitADesCongesPayesAuxLimite10(){
        // Given
        SalarieAideADomicile aide = new SalarieAideADomicile("Jack", LocalDate.now(), LocalDate.now(), 0, 0, 10, 1, 2);

        //WHEN
        boolean res = aide.aLegalementDroitADesCongesPayes();

        //THEN
        Assertions.assertEquals(true, res);
    }

    @Test
    void aLegalementDroitADesCongesPayesAuxLimite9demi(){
        // Given
        SalarieAideADomicile aide = new SalarieAideADomicile("Jack", LocalDate.now(), LocalDate.now(), 0, 0, 9.5, 1, 2);

        //WHEN
        boolean res = aide.aLegalementDroitADesCongesPayes();

        //THEN
        Assertions.assertEquals(false, res);
    }

    @Test
    void aLegalementDroitADesCongesPayesAuxLimite0(){
        // Given
        SalarieAideADomicile aide = new SalarieAideADomicile("Jack", LocalDate.now(), LocalDate.now(), 0, 0, 0, 0, 0);

        //WHEN
        boolean res = aide.aLegalementDroitADesCongesPayes();

        //THEN
        Assertions.assertEquals(false, res);
    }


    @ParameterizedTest(name = "Entre {0} et {1} est valide : {1}")
    @CsvSource({
            "'2022-07-01', '2022-07-02', 2",
            "'2022-07-01', '2022-07-03', 2",
            "'2022-07-02', '2022-07-04', 1",
            "'2022-07-02', '2022-07-02', 0",
            "'2022-07-03', '2022-07-02', 0"
    })
    void calculeJoursDeCongeDecomptesPourPlage(String debut, String fin, double expectedNBJoursDeCongeDecompe){
        //Given
        SalarieAideADomicile aide = new SalarieAideADomicile("Jack", LocalDate.now(), LocalDate.now(), 0, 0, 15, 1, 2);
        LocalDate dateDebut = LocalDate.parse(debut);
        LocalDate dateFin = LocalDate.parse(fin);

        //When
        LinkedHashSet<LocalDate> res = aide.calculeJoursDeCongeDecomptesPourPlage(dateDebut, dateFin);

        //Then
        Assertions.assertEquals(expectedNBJoursDeCongeDecompe, res.size());
    }
    @Test
    void calculeJoursDeCongeDecomptesPourPlageFinAvantDebut(){
        //Given
        SalarieAideADomicile aide = new SalarieAideADomicile("Jack", LocalDate.now(), LocalDate.now(), 0, 0, 15, 1, 2);
        LocalDate dateFin = LocalDate.of(2022, 3, 15);
        LocalDate dateDebut = LocalDate.of(2022, 3, 17);

        //When
        LinkedHashSet<LocalDate> date = aide.calculeJoursDeCongeDecomptesPourPlage(dateDebut, dateFin);

        //Then
        LinkedHashSet<LocalDate> dates = new LinkedHashSet<>();
        Assertions.assertEquals(dates, date);
    }
    @Test
    void calculeJoursDeCongeDecomptesPourPlageDebutEgalFin(){
        //Given
        SalarieAideADomicile aide = new SalarieAideADomicile("Jack", LocalDate.now(), LocalDate.now(), 0, 0, 15, 1, 2);
        LocalDate dateFin = LocalDate.of(2022, 3, 15);
        LocalDate dateDebut = LocalDate.of(2022, 3, 15);

        //When
        LinkedHashSet<LocalDate> date = aide.calculeJoursDeCongeDecomptesPourPlage(dateDebut, dateFin);

        //Then
        LinkedHashSet<LocalDate> dates = new LinkedHashSet<>();
        dates.add(LocalDate.of(2022, 3, 15));
        Assertions.assertEquals(dates, date);
    }
    @Test
    void calculeJoursDeCongeDecomptesPourPlageSansConge(){
        //Given
        SalarieAideADomicile aide = new SalarieAideADomicile("Jack", LocalDate.now(), LocalDate.now(), 0, 0, 0, 0, 0);
        LocalDate dateDebut = LocalDate.of(2022, 3, 15);
        LocalDate dateFin = LocalDate.of(2022, 3, 17);

        //When
        LinkedHashSet<LocalDate> date = aide.calculeJoursDeCongeDecomptesPourPlage(dateDebut, dateFin);

        //Then
        LinkedHashSet<LocalDate> dates = new LinkedHashSet<>();
        dates.add(LocalDate.of(2022, 3, 15));
        dates.add(LocalDate.of(2022, 3, 16));
        //2eme methode pour instancié une LocalDate
        dates.add(LocalDate.parse("2022-03-17"));
        Assertions.assertEquals(dates, date);
    }
}
