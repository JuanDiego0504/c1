package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class InventarioPK implements Serializable {
    
    //TODO revisar si se rompe con OneToOne... es más accurate al problema
    @ManyToOne 
    @JoinColumn(name="id_producto", referencedColumnName = "id")
    private Producto id_producto;

     //TODO revisar si se rompe con OneToOne... es más accurate al problema
    @ManyToOne
    @JoinColumn(name="id_bodega", referencedColumnName = "id")
    private Bodega id_bodega; 

    private Double costoPromedio;

    private Integer capacidad;

    private Integer numeroReorden;

    public InventarioPK(){
        super();
    }

    public InventarioPK(Producto id_producto, Bodega id_bodega, Double costoPromedio, Integer capacidad, Integer numeroReorden) {
        super();
        this.id_producto = id_producto;
        this.id_bodega = id_bodega;
        this.costoPromedio = costoPromedio;
        this.capacidad = capacidad;
        this.numeroReorden = numeroReorden;
    }

    public Double getCostoPromedio() {
        return costoPromedio;
    }

    public void setCostoPromedio(Double costoPromedio) {
        this.costoPromedio = costoPromedio;
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

    public Producto getId_producto() {
        return id_producto;
    }

    public void setId_producto(Producto id_producto) {
        this.id_producto = id_producto;
    }

    public Bodega getId_bodega() {
        return id_bodega;
    }

    public void setId_bodega(Bodega id_bodega) {
        this.id_bodega = id_bodega;
    }
    
}
