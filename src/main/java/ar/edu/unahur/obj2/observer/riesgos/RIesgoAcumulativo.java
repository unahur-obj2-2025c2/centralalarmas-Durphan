package ar.edu.unahur.obj2.observer.riesgos;

import java.util.List;

import ar.edu.unahur.obj2.alertas.Alerta;

public class RIesgoAcumulativo implements ITipoRiesgo {

    private static RIesgoAcumulativo instancia = new RIesgoAcumulativo();

    @Override
    public double factorDeRiesgo(ar.edu.unahur.obj2.observer.entidades.Entity entidad) {
        List<Alerta> alertasCriticas = entidad.getAlertasRecibidas().stream()
                .filter(Alerta::esCritica)
                .toList();
        return alertasCriticas.stream().mapToInt(Alerta::getNivel).sum();
    }

    public static RIesgoAcumulativo getInstancia() {
        return instancia;
    }

}
