package uniandes.edu.co.proyecto.modelo;

import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordenesDeCompra")
public class OrdenDeCompra {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer cantidadProducto;
    private Integer precioProducto;
    private Date fechaEntregaEsperada;

    @ManyToOne
    @JoinColumn(name = "sucursal", nullable = false) 
    private Sucursal sucursal;

    @ManyToOne
    @JoinColumn(name = "proveedor", nullable = false) 
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "producto", nullable = false) 
    private Producto producto;

    // Constructor sin parámetros
    public OrdenDeCompra() {}

    // Constructor que acepta todos los parámetros menos el id
    public OrdenDeCompra(Integer cantidadProducto, Integer precioProducto, Sucursal sucursal, Proveedor proveedor, Producto producto) {
        this.cantidadProducto = cantidadProducto;
        this.precioProducto = precioProducto;
        this.sucursal = sucursal;
        this.proveedor = proveedor;
        this.producto = producto;
    }

    // Constructor que acepta solo el id (necesario para el controlador)
    public OrdenDeCompra(Integer id) {
        this.id = id;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Integer getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(Integer precioProducto) {
        this.precioProducto = precioProducto;
    }

    public Date getFechaEntregaEsperada() {
        return fechaEntregaEsperada;
    }

    public void setFechaEntregaEsperada(Date fechaEntregaEsperada) {
        this.fechaEntregaEsperada = fechaEntregaEsperada;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
