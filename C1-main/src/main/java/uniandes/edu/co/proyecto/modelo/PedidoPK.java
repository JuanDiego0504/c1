package uniandes.edu.co.proyecto.modelo;
import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class PedidoPK implements Serializable {
    
    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    private Producto id_producto;

    @ManyToOne
    @JoinColumn(name="id_ordenDeCompra", referencedColumnName = "id")
    private OrdenDeCompra id_ordenDeCompra;

    private Date fechaEntrega;

    private String estado;

    public PedidoPK(){
        super();
    }

    public PedidoPK(Producto id_producto, OrdenDeCompra id_ordenDeCompra, Date fechaEntrega, String estado) {
        super();
        this.id_producto = id_producto;
        this.id_ordenDeCompra = id_ordenDeCompra;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
    }

    public Producto getId_producto() {
        return id_producto;
    }

    public void setId_producto(Producto id_producto) {
        this.id_producto = id_producto;
    }

    public OrdenDeCompra getId_ordenDeCompra() {
        return id_ordenDeCompra;
    }

    public void setId_ordenDeCompra(OrdenDeCompra id_ordenDeCompra) {
        this.id_ordenDeCompra = id_ordenDeCompra;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
