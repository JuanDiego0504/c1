package uniandes.edu.co.proyecto.modelo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "proveedores")
public class Proveedor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer nit;

    private String nombre;

    private String direccion;

    private String personaContacto;

    private String telefonoContacto;

    @OneToMany(mappedBy = "proveedor")
    private List<OrdenDeCompra> ordenesDeCompra;

    public Proveedor()
    {;}

    public Proveedor(Integer nit, String nombre, String direccion, String personaContacto, String telefonoContacto) {
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.personaContacto = personaContacto;
        this.telefonoContacto = telefonoContacto;
    }

    public Integer getNit() {
        return nit;
    }

    public void setNit(Integer nit) {
        this.nit = nit;
    }

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

    public String getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<OrdenDeCompra> getOrdenesDeCompra() {
        return ordenesDeCompra;
    }

    public void setOrdenesDeCompra(List<OrdenDeCompra> ordenesDeCompra) {
        this.ordenesDeCompra = ordenesDeCompra;
    }
    
}
