package ar.edu.unahur.obj2.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ar.edu.unahur.obj2.alertas.Alerta;
import ar.edu.unahur.obj2.observer.entidades.Entity;

public class CentralAlarmas {
    private List<Entity> subscriptores = new ArrayList<>();
    private HashMap<Alerta, List<Entity>> alertasEnviadas = new HashMap<>();

    public void agregarSubscriptor(Entity entidad) {
        this.subscriptores.add(entidad);
    }

    public void saltarAlarma(String tipo, Integer nivel) {
        if (nivel < 1 || nivel > 10) {
            throw new IllegalArgumentException("Nivel de alerta inválido: " + nivel + ". Debe estar entre 1 y 10.");
        }
        Alerta alerta = new Alerta(nivel, tipo);
        alertasEnviadas.put(alerta, this.subscriptores);
        subscriptores.stream().forEach(entidad -> entidad.recibirAlerta(alerta));
    }

    public HashMap<Alerta, List<Entity>> getAlertasEnviadas() {
        return alertasEnviadas;
    }

    public Integer cantidadDeAlertasEnviadas() {
        return alertasEnviadas.size();
    }

}