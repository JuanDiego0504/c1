package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="productos")
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "producto_generator")
    @SequenceGenerator(name = "producto_generator", sequenceName = "productos_SEQ", allocationSize = 1)
    private Long id;

    private Integer codigoDeBarras;
    private String nombre;
    private Integer costoEnBodega;
    private Integer precioUnitario;
    private String presentacion;
    private Integer cantidadPresentacion;
    private String unidadPresentacion;
    private String especEmpaque;
    private Date fechaVencimiento;

    public Producto() {}

    // Constructor que acepta Long
    public Producto(Long id) {
        this.id = id;
    }

    // Constructor que acepta Integer y convierte a Long
    public Producto(Integer id) {
        this.id = id != null ? id.longValue() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(Integer codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCostoEnBodega() {
        return costoEnBodega;
    }

    public void setCostoEnBodega(Integer costoEnBodega) {
        this.costoEnBodega = costoEnBodega;
    }

    public Integer getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Integer precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public Integer getCantidadPresentacion() {
        return cantidadPresentacion;
    }

    public void setCantidadPresentacion(Integer cantidadPresentacion) {
        this.cantidadPresentacion = cantidadPresentacion;
    }

    public String getUnidadPresentacion() {
        return unidadPresentacion;
    }

    public void setUnidadPresentacion(String unidadPresentacion) {
        this.unidadPresentacion = unidadPresentacion;
    }

    public String getEspecEmpaque() {
        return especEmpaque;
    }

    public void setEspecEmpaque(String especEmpaque) {
        this.especEmpaque = especEmpaque;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}
