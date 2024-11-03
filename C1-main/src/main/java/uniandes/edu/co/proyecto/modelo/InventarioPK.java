package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class InventarioPK implements Serializable {
    
    @ManyToOne
    @JoinColumn(name="id_producto", referencedColumnName = "id")
    private Producto id_producto;

    @ManyToOne
    @JoinColumn(name="id_bodega", referencedColumnName = "id")
    private Bodega id_bodega;

    public InventarioPK() {
        super();
    }

    public InventarioPK(Producto id_producto, Bodega id_bodega) {
        this.id_producto = id_producto;
        this.id_bodega = id_bodega;
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

    // Implementación de equals y hashCode es necesaria para que funcione como clave primaria compuesta
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InventarioPK that = (InventarioPK) o;

        if (!id_producto.equals(that.id_producto)) return false;
        return id_bodega.equals(that.id_bodega);
    }

    @Override
    public int hashCode() {
        int result = id_producto.hashCode();
        result = 31 * result + id_bodega.hashCode();
        return result;
    }
}
