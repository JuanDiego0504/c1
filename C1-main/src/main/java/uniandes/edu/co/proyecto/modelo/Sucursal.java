package uniandes.edu.co.proyecto.modelo;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "sucursales")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sucursal_generator")
    @SequenceGenerator(name = "sucursal_generator", sequenceName = "sucursales_SEQ", allocationSize = 1)
    private Integer id;  // Cambiado a Integer

    private String nombre;
    private String direccion;
    private String telefono;
    private Integer tamanio;

    @ManyToOne
    @JoinColumn(name = "ciudad", referencedColumnName = "id")
    private Ciudad ciudad;

    @OneToMany(mappedBy = "sucursal")
    private List<OrdenDeCompra> ordenesDeCompra;

    public Sucursal() {}

    public Sucursal(String nombre, String direccion, String telefono, Integer tamanio, Ciudad ciudad) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tamanio = tamanio;
        this.ciudad = ciudad;
    }

    public Integer getId() {  // Cambiado a Integer
        return id;
    }

    public void setId(Integer id) {  // Cambiado a Integer
        this.id = id;
    }

    // Getters y setters restantes
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getTamanio() {
        return tamanio;
    }

    public void setTamanio(Integer tamanio) {
        this.tamanio = tamanio;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public List<OrdenDeCompra> getOrdenesDeCompra() {
        return ordenesDeCompra;
    }

    public void setOrdenesDeCompra(List<OrdenDeCompra> ordenesDeCompra) {
        this.ordenesDeCompra = ordenesDeCompra;
    }
}
