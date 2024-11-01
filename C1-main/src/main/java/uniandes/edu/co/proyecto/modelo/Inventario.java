package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="inventarios")
public class Inventario {

    @EmbeddedId
    private InventarioPK pk;

    public Inventario()
    {;}

    public Inventario(Producto id_producto, Bodega id_bodega, Double costoPromedio, Integer capacidad, Integer numeroReorden){
        this.pk = new InventarioPK(id_producto, id_bodega, costoPromedio, capacidad, numeroReorden);
    }

    public InventarioPK getPk() {
        return pk;
    }

    public void setPk(InventarioPK pk) {
        this.pk = pk;
    }
}
