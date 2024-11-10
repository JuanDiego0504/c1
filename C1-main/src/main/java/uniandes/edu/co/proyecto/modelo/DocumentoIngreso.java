package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import java.util.Date;

@Entity
@Table(name = "DOCUMENTOS_INGRESO")
public class DocumentoIngreso {

    @Id
    private Long id;

    @Column(name = "numero_documento", nullable = false)
    private String numeroDocumento;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "bodega_id", nullable = false)
    private Bodega bodega;

    // Constructor vacío
    public DocumentoIngreso() { }

    // Constructor con parámetros
    public DocumentoIngreso(Long id, String numeroDocumento, Date fecha, Proveedor proveedor, Bodega bodega) {
        this.id = id;
        this.numeroDocumento = numeroDocumento;
        this.fecha = fecha;
        this.proveedor = proveedor;
        this.bodega = bodega;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

  
}