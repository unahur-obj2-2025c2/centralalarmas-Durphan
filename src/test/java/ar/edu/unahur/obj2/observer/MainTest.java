package ar.edu.unahur.obj2.observer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.observer.entidades.Entity;
import ar.edu.unahur.obj2.observer.riesgos.RIesgoAcumulativo;
import ar.edu.unahur.obj2.observer.riesgos.RiesgoPromedio;

public class MainTest {
    CentralAlarmas centralAlarmas = new CentralAlarmas();
    Entity hospital = new Entity("Hospital");
    Entity comisaria = new Entity("Comisaria");

    @BeforeEach
    public void setUp() {
        hospital.suscribirse(centralAlarmas);
        comisaria.suscribirse(centralAlarmas);
    }

    @Test
    public void recibirAlertaCalorYLluvia() {
        centralAlarmas.saltarAlarma("calor", 6);
        centralAlarmas.saltarAlarma("lluvia", 8);
        assertEquals(2, hospital.getAlertasRecibidas().size());
        assertEquals(2, comisaria.getAlertasRecibidas().size());
        assertEquals(10, hospital.factorDeRiesgo());
        assertEquals(10, comisaria.factorDeRiesgo());
    }

    @Test
    public void factorDeRiesgoEsDistintoSiEsRiesgoPromedio() {
        hospital.setTipoRiesgo(RiesgoPromedio.getInstancia());
        centralAlarmas.saltarAlarma("calor", 6);
        centralAlarmas.saltarAlarma("lluvia", 8);
        assertEquals(7, hospital.factorDeRiesgo());
        assertEquals(10, comisaria.factorDeRiesgo());
    }

    @Test
    public void sePuedeDesuscribirUnaEntidad() {
        centralAlarmas.saltarAlarma("calor", 6);
        centralAlarmas.saltarAlarma("lluvia", 8);
        centralAlarmas.desuscribir(hospital);
        centralAlarmas.saltarAlarma("granizo", 7);
        assertEquals(2, hospital.getAlertasRecibidas().size());
        assertEquals(10, hospital.factorDeRiesgo());
        assertEquals(3, comisaria.getAlertasRecibidas().size());
        assertEquals(7, comisaria.factorDeRiesgo());
        assertEquals(3, centralAlarmas.cantidadDeAlertasEnviadas());
    }

    @Test
    public void noSePuedenEnviarAlertasDeNivelNegativoOMayorA10() {
        assertThrows(IllegalArgumentException.class, () -> centralAlarmas.saltarAlarma("calor", -1));
        assertThrows(IllegalArgumentException.class, () -> centralAlarmas.saltarAlarma("calor", 11));
    }

    @Test
    public void entidadRespondeAlertaMasPrioritariaSiHay2ConIgualNivelRespondeLaMasAntigua() {
        centralAlarmas.saltarAlarma("calor", 5);
        centralAlarmas.saltarAlarma("lluvia", 8);
        centralAlarmas.saltarAlarma("granizo", 8);
        assertEquals(3, comisaria.getAlertasRecibidas().size());
        comisaria.responderAlerta();
        assertEquals(2, comisaria.getAlertasRecibidas().size());
        assertEquals(5, comisaria.getAlertasRecibidas().get(0).getNivel());
        assertEquals(8, comisaria.getAlertasRecibidas().get(1).getNivel());
        assertEquals("granizo", comisaria.getAlertasRecibidas().get(1).getDescripcion());
        comisaria.responderAlerta();
        assertEquals(1, comisaria.getAlertasRecibidas().size());
        assertEquals(5, comisaria.getAlertasRecibidas().get(0).getNivel());
    }

    @Test
    public void testRiesgoAcumulativo() {
        hospital.setTipoRiesgo(RIesgoAcumulativo.getInstancia());
        centralAlarmas.saltarAlarma("calor", 4);
        centralAlarmas.saltarAlarma("lluvia", 6);
        centralAlarmas.saltarAlarma("granizo", 8);
        assertEquals(8, hospital.factorDeRiesgo());
    }
}
