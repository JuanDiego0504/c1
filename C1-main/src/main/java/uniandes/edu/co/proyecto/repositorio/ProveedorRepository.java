package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import  org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Proveedor;

public interface ProveedorRepository extends JpaRepository <Proveedor, Integer> {

    @Query(value = "SELECT * FROM proveedores", nativeQuery = true)
    Collection<Proveedor> darProveedores();

    @Query(value = "SELECT * FROM proveedores WHERE id= :id", nativeQuery = true)
    Proveedor darProveedor(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO proveedores (id, nit, nombre, direccion, personaContacto, telefonoContacto) VALUES (superandes_sequence.nextval, :nit, :nombre, :direccion, :personaContacto, :telefonoContacto)", nativeQuery = true)
    void insertarProveedor(@Param("nombre") Integer nit, @Param("nombre") String nombre, @Param("direccion") String direccion, @Param("personaContacto") String personaContacto, @Param("telefonoContacto") String telefonoContacto);

    @Modifying
    @Transactional
    @Query(value = "UPDATE proveedores SET nit= :nit, nombre= :nombre, direccion= :direccion, personaContacto= :personaContacto, telefonoContacto= :telefonoContacto WHERE id =:id", nativeQuery = true)
    void actualizarProveedor(@Param("id") Integer id, @Param("nombre") Integer nit, @Param("nombre") String nombre, @Param("direccion") String direccion, @Param("personaContacto") String personaContacto, @Param("telefonoContacto") String telefonoContacto);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM proveedores WHERE id = :id", nativeQuery = true)
    void eliminarProveedor(@Param("id") Integer id);
}
