package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="inventarios")
public class Inventario {

    @EmbeddedId
    private InventarioPK pk;

    private Double costoPromedio;
    private Integer cantidad; // Agregar este atributo aquí
    private Integer capacidad;
    private Integer numeroReorden;

    public Inventario() { }

    public Inventario(Producto id_producto, Bodega id_bodega, Double costoPromedio, Integer cantidad, Integer capacidad, Integer numeroReorden){
        this.pk = new InventarioPK(id_producto, id_bodega);
        this.costoPromedio = costoPromedio;
        this.cantidad = cantidad;
        this.capacidad = capacidad;
        this.numeroReorden = numeroReorden;
    }

    public InventarioPK getPk() {
        return pk;
    }

    public void setPk(InventarioPK pk) {
        this.pk = pk;
    }

    public Double getCostoPromedio() {
        return costoPromedio;
    }

    public void setCostoPromedio(Double costoPromedio) {
        this.costoPromedio = costoPromedio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getNumeroReorden() {
        return numeroReorden;
    }

    public void setNumeroReorden(Integer numeroReorden) {
        this.numeroReorden = numeroReorden;
    }
}
