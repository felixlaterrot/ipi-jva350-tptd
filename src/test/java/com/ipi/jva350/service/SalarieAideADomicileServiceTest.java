package com.ipi.jva350.service;

import com.ipi.jva350.exception.SalarieException;
import com.ipi.jva350.model.SalarieAideADomicile;
import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SalarieAideADomicileServiceTest {

    @Autowired
    private SalarieAideADomicileService salarie;
    private SalarieAideADomicileRepository salarieAideADomicileRepository;

    @Test
    void clotureMois() throws SalarieException {
        SalarieAideADomicile aide = new SalarieAideADomicile("Jack", LocalDate.now(), LocalDate.now(), 0, 0, 15, 1, 2);
        salarie.clotureMois(aide,20);
        Assertions.assertEquals(20, aide.getJoursTravaillesAnneeN());
    }

    @Test
    void calculeLimiteEntrepriseCongesPermis() {
        //GIVEN
        //SalarieAideADomicile salaireAideADomicile = new SalarieAideADomicile("Jack", LocalDate.now(), LocalDate.now(), 0, 0, 15, 1, 2);

        LocalDate moisEnCours = LocalDate.now();

        LocalDate debut = LocalDate.of(2022, 3, 23);
        LocalDate premierJour = LocalDate.of(2022, 3, 15);
        LocalDate dernierJour = LocalDate.of(2022, 5, 23);

        //WHEN
        //salarieAideADomicileRepository.save(salaireAideADomicile);
        //Double conges = salarieAideADomicileRepository.partCongesPrisTotauxAnneeNMoins1();

        long res = salarie.calculeLimiteEntrepriseCongesPermis(moisEnCours, 1d, debut, premierJour, dernierJour);

        //THEN
        Assertions.assertEquals(20, res);
    }
}