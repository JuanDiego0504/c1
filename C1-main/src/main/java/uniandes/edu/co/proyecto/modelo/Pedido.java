package uniandes.edu.co.proyecto.modelo;
import java.sql.Date;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "pedidos")
public class Pedido {
    
    @EmbeddedId
    private PedidoPK pk;

    public Pedido()
    {;}

    public Pedido(Producto id_producto, OrdenDeCompra id_ordenDeCompra, Date fechaEntrega, String estado){
        this.pk = new PedidoPK(id_producto, id_ordenDeCompra, fechaEntrega, estado);
    }

    public PedidoPK getPk() {
        return pk;
    }

    public void setPk(PedidoPK pk) {
        this.pk = pk;
    }
}
