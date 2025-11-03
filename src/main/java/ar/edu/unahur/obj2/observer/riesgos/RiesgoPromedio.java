package ar.edu.unahur.obj2.observer.riesgos;

import java.util.List;

import ar.edu.unahur.obj2.alertas.Alerta;
import ar.edu.unahur.obj2.observer.entidades.Entity;

public class RiesgoPromedio implements ITipoRiesgo {
    private static RiesgoPromedio instancia = new RiesgoPromedio();

    @Override
    public double factorDeRiesgo(Entity entidad) {
        List<Alerta> alertas = entidad.getAlertasRecibidas();
        return alertas.stream().mapToInt(Alerta::getNivel).average().orElse(0);
    }

    public static RiesgoPromedio getInstancia() {
        return instancia;
    }

}
