package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import  org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Sucursal;

public interface SucursalRepository extends JpaRepository <Sucursal, Integer> {

    @Query(value = "SELECT * FROM sucursales", nativeQuery = true)
    Collection<Sucursal> darSucursals();

    @Query(value = "SELECT * FROM sucursales WHERE id= :id", nativeQuery = true)
    Sucursal darSucursal(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO sucursales (id, nombre, direccion, telefono, tamanio) VALUES (superandes_sequence.nextval, :nombre, :direccion, :telefono, :tamanio)", nativeQuery = true)
    void insertarSucursal(@Param("nombre") String nombre, @Param("direccion") String direccion, @Param("telefono") String telefono, @Param("tamanio") Integer tamanio);

    @Modifying
    @Transactional
    @Query(value = "UPDATE sucursales SET nombre= :nombre, direccion= :direccion, telefono= :telefono, tamanio= :tamanio WHERE id =:id", nativeQuery = true)
    void actualizarSucursal(@Param("id") Integer id, @Param("nombre") String nombre, @Param("direccion") String direccion, @Param("telefono") String telefono, @Param("tamanio") Integer tamanio);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM sucursales WHERE id = :id", nativeQuery = true)
    void eliminarSucursal(@Param("id") Integer id);
}
