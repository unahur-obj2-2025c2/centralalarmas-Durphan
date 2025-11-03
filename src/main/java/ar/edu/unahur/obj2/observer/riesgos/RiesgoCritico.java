package ar.edu.unahur.obj2.observer.riesgos;

import java.util.List;

import ar.edu.unahur.obj2.alertas.Alerta;
import ar.edu.unahur.obj2.observer.entidades.Entity;

public class RiesgoCritico implements ITipoRiesgo {
    private static RiesgoCritico instancia = new RiesgoCritico();

    @Override
    public double factorDeRiesgo(Entity entidad) {
        List<Alerta> alertas = entidad.getAlertasRecibidas();
        if (alertas.get(alertas.size() - 1).esCritica()) {
            return 10;
        }
        return alertas.get(alertas.size() - 1).getNivel();
    }

    public static RiesgoCritico getInstancia() {
        return instancia;
    }

}
