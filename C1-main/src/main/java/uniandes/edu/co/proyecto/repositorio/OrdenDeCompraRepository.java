package uniandes.edu.co.proyecto.repositorio;

import java.sql.Date;
import java.util.Collection;

import  org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.OrdenDeCompra;

public interface OrdenDeCompraRepository extends JpaRepository <OrdenDeCompra, Integer> {

    @Query(value = "SELECT * FROM ordenesDeCompra", nativeQuery = true)
    Collection<OrdenDeCompra> darOrdenesDeCompra();

    @Query(value = "SELECT * FROM ordenesDeCompra WHERE id= :id", nativeQuery = true)
    OrdenDeCompra darOrdenDeCompra(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ordenesDeCompra (id, cantidadProducto, precioProducto, fechaEntregaEsperada, sucursal, proveedor, producto) VALUES (superandes_sequence.nextval, :cantidadProducto, :precioProducto, :fechaEntregaEsperada, :sucursal, :proveedor, :producto)", nativeQuery = true)
    void insertarOrdenDeCompra(@Param("cantidadProducto") Integer cantidadProducto, @Param("precioProducto") Integer precioProducto, @Param("fechaEntregaEsperada") Date fechaEntregaEsperada,@Param("sucursal") Integer sucursal, @Param("proveedor") Integer proveedor, @Param("producto") Integer producto);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ordenesDeCompra SET cantidadProducto= :cantidadProducto, precioProducto= :precioProducto, fechaEntregaEsperada= :fechaEntregaEsperada, sucursal= :sucursal, proveedor= :proveedor, producto= :producto  WHERE id =:id", nativeQuery = true)
    void actualizarOrdenDeCompra(@Param("id") Integer id, @Param("cantidadProducto") Integer cantidadProducto, @Param("precioProducto") Integer precioProducto, @Param("fechaEntregaEsperada") Date fechaEntregaEsperada,@Param("sucursal") Integer sucursal, @Param("proveedor") Integer proveedor, @Param("producto") Integer producto);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ordenesDeCompra WHERE id = :id", nativeQuery = true)
    void eliminarOrdenDeCompra(@Param("id") Integer id);
}
