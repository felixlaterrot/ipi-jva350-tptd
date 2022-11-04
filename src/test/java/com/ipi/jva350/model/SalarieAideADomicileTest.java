package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class SalarieAideADomicileTest {

    @Test
    void aLegalementDroitADesCongesPayes(){
        // Given
        SalarieAideADomicile aide = new SalarieAideADomicile("Jack", LocalDate.now(), LocalDate.now(), 0, 0, 5, 1, 0);

        //WHEN
        boolean res = aide.aLegalementDroitADesCongesPayes();

        //THEN
        Assertions.assertEquals(false, res);
    }
}
