package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer codigo;

    private String nombre;

    private String descripcion;

    private String caracteristicasAlmacenamiento;

    public Categoria()
    {;}

    public Categoria(Integer codigo, String nombre, String descripcion, String caracteristicasAlmacenamiento) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.caracteristicasAlmacenamiento = caracteristicasAlmacenamiento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCaracteristicasAlmacenamiento() {
        return caracteristicasAlmacenamiento;
    }

    public void setCaracteristicasAlmacenamiento(String caracteristicasAlmacenamiento) {
        this.caracteristicasAlmacenamiento = caracteristicasAlmacenamiento;
    }

}
