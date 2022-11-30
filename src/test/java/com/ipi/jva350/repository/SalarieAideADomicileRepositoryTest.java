package com.ipi.jva350.repository;

import com.ipi.jva350.exception.SalarieException;
import com.ipi.jva350.model.SalarieAideADomicile;
import com.ipi.jva350.service.SalarieAideADomicileService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
// Junit 4 : @RunWith(SpringRunner.class)
@SpringBootTest
class SalarieAideADomicileRepositoryTest {

    @Autowired
    SalarieAideADomicileRepository salarie;
    @Test
    void findByNomTestToto() {
        //Given
        SalarieAideADomicile salaireAideADomicile = new SalarieAideADomicile("Jack", LocalDate.now(), LocalDate.now(), 0, 0, 15, 1, 2);
        //When
        salarie.save(salaireAideADomicile);
        SalarieAideADomicile res = salarie.findByNom(salaireAideADomicile.getNom());
        //Then
        Assertions.assertEquals("Jack", res.getNom());
    }
    @Test
    void partCongesPrisTotauxAnneeNMoins1SalarieWith2() {
        //Given
        SalarieAideADomicile salaireAideADomicile = new SalarieAideADomicile("Jack", LocalDate.now(), LocalDate.now(), 0, 0, 15, 1, 2);
        //When
        salarie.save(salaireAideADomicile);
        Double res = salarie.partCongesPrisTotauxAnneeNMoins1();
        //Then
        Assertions.assertEquals(2, res);
    }
    @Test
    void partCongesPrisTotauxAnneeNMoins1SalarieWith0() {
        //Given
        SalarieAideADomicile salaireAideADomicile = new SalarieAideADomicile("Jack", LocalDate.now(), LocalDate.now(), 0, 0, 15, 1, 0);
        //When
        salarie.save(salaireAideADomicile);
        Double res = salarie.partCongesPrisTotauxAnneeNMoins1();
        //Then
        Assertions.assertEquals(0, res);
    }
    @Test
    void partCongesPrisTotauxAnneeNMoins1SalarieWithNegatif() {
        //Given
        SalarieAideADomicile salaireAideADomicile = new SalarieAideADomicile("Jack", LocalDate.now(), LocalDate.now(), 0, 0, 15, 1, -1);
        //When
        salarie.save(salaireAideADomicile);
        Double res = salarie.partCongesPrisTotauxAnneeNMoins1();
        //Then
        Assertions.assertEquals(-1, res);
    }
}