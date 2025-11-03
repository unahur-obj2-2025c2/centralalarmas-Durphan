package ar.edu.unahur.obj2.observer.entidades;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.alertas.Alerta;
import ar.edu.unahur.obj2.observer.CentralAlarmas;
import ar.edu.unahur.obj2.observer.riesgos.ITipoRiesgo;
import ar.edu.unahur.obj2.observer.riesgos.RiesgoCritico;

public class Entity {
    private final String name;
    private List<Alerta> alertasRecibidas = new ArrayList<>();
    private ITipoRiesgo tipoRiesgo = RiesgoCritico.getInstancia();

    public Entity(String name, ITipoRiesgo tipoRiesgo) {
        this.name = name;
        this.tipoRiesgo = tipoRiesgo;
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

    public String getName() {
        return name;
    }

    public void setTipoRiesgo(ITipoRiesgo tipoRiesgo) {
        this.tipoRiesgo = tipoRiesgo;
    }

    public void suscribirse(CentralAlarmas central) {
        central.agregarSubscriptor(this);
    }

}
