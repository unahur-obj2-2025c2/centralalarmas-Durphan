package ar.edu.unahur.obj2.alertas;

public class Alerta {
    private Integer nivel;

    private final String descripcion;

    public Alerta(Integer nivel, String descripcion) {
        this.nivel = nivel;
        this.descripcion = descripcion;
    }

    public Integer getNivel() {
        return nivel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Boolean esCritica() {
        return Boolean.valueOf(this.nivel >= 8);
    }

}
