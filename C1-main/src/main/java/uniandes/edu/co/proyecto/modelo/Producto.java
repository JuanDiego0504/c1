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
@Table(name="productos")
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer codigoDeBarras;

    private String nombre;

    private Integer costoEnBodega;

    private Integer precioUnitario;

    private String presentacion;

    private Integer cantidadPresentacion;

    private String unidadPresentacion;

    private String especEmpaque;

    private Date fechaVencimiento;

    @ManyToOne
    @JoinColumn(name = "categoria", referencedColumnName = "id")
    private Categoria categoria;

    public Producto()
    {;}

    public Producto(Integer codigoDeBarras, String nombre, Integer costoEnBodega, Integer precioUnitario,
            String presentacion, Integer cantidadPresentacion, String unidadPresentacion, String especEmpaque,
            Date fechaVencimiento, Categoria categoria) {
        this.codigoDeBarras = codigoDeBarras;
        this.nombre = nombre;
        this.costoEnBodega = costoEnBodega;
        this.precioUnitario = precioUnitario;
        this.presentacion = presentacion;
        this.cantidadPresentacion = cantidadPresentacion;
        this.unidadPresentacion = unidadPresentacion;
        this.especEmpaque = especEmpaque;
        this.fechaVencimiento = fechaVencimiento;
        this.categoria = categoria;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}
