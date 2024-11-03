package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name="productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "producto_generator")
    @SequenceGenerator(name = "producto_generator", sequenceName = "productos_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "CODIGODEBARRAS")
    private Integer codigoDeBarras;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "COSTOENBODEGA")
    private Integer costoEnBodega;

    @Column(name = "PRECIOUNITARIO")
    private Integer precioUnitario;

    @Column(name = "PRESENTACION")
    private String presentacion;

    @Column(name = "CANTIDADPRESENTACION")
    private Integer cantidadPresentacion;

    @Column(name = "UNIDADPRESENTACION")
    private String unidadPresentacion;

    @Column(name = "ESPECEMPAQUE")
    private String especEmpaque;

    @Column(name = "FECHAVENCIMIENTO")
    private Date fechaVencimiento;

    @ManyToOne
    @JoinColumn(name = "categoria", referencedColumnName = "id")
    private Categoria categoria;

    // Constructor sin parámetros
    public Producto() {}

    // Constructor que acepta Long
    public Producto(Long id) {
        this.id = id;
    }

    // Constructor que acepta Integer (para compatibilidad con PedidoController)
    public Producto(Integer id) {
        this.id = id != null ? id.longValue() : null;
    }

    // Getters y Setters

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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", codigoDeBarras=" + codigoDeBarras +
                ", nombre='" + nombre + '\'' +
                ", costoEnBodega=" + costoEnBodega +
                ", precioUnitario=" + precioUnitario +
                ", presentacion='" + presentacion + '\'' +
                ", cantidadPresentacion=" + cantidadPresentacion +
                ", unidadPresentacion='" + unidadPresentacion + '\'' +
                ", especEmpaque='" + especEmpaque + '\'' +
                ", fechaVencimiento=" + fechaVencimiento +
                ", categoria=" + (categoria != null ? categoria.getNombre() : "Sin Categoría") +
                '}';
    }
}
