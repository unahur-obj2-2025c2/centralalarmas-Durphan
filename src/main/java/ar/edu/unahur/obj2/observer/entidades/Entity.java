package ar.edu.unahur.obj2.observer.entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

import ar.edu.unahur.obj2.alertas.Alerta;
import ar.edu.unahur.obj2.observer.CentralAlarmas;
import ar.edu.unahur.obj2.observer.riesgos.ITipoRiesgo;
import ar.edu.unahur.obj2.observer.riesgos.RiesgoCritico;

public class Entity {
    private final String name;
    private List<Alerta> alertasRecibidas = new ArrayList<>();
    private ITipoRiesgo tipoRiesgo = RiesgoCritico.getInstancia();

    public Entity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Alerta> getAlertasRecibidas() {
        return alertasRecibidas;
    }

    public void recibirAlerta(Alerta alerta) {
        this.alertasRecibidas.add(alerta);
    }

    public double factorDeRiesgo() {
        return this.tipoRiesgo.factorDeRiesgo(this);
    }

    public void setTipoRiesgo(ITipoRiesgo tipoRiesgo) {
        this.tipoRiesgo = tipoRiesgo;
    }

    public void suscribirse(CentralAlarmas central) {
        central.agregarSubscriptor(this);
    }

    public void responderAlerta() {

        Alerta alertaMasPrioritaria = this.alertasRecibidas.stream()
                .max(Comparator.comparing(Alerta::getNivel))
                .orElse(null);
        this.alertasRecibidas.remove(alertaMasPrioritaria);
    }

}
