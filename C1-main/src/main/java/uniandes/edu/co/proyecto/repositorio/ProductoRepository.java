package uniandes.edu.co.proyecto.repositorio;

import java.sql.Date;
import java.util.Collection;

import  org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Producto;

public interface ProductoRepository extends JpaRepository <Producto, Integer> {

    @Query(value = "SELECT * FROM productos", nativeQuery = true)
    Collection<Producto> darProductos();

    @Query(value = "SELECT * FROM productos WHERE id= :id", nativeQuery = true)
    Producto darProducto(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO productos (id, codigoDeBarras, nombre, costoEnBodega, precioUnitario, presentacion, cantidadPresentacion, unidadPresentacion, especEmpaque, fechaVencimiento, categoria) VALUES (superandes_sequence.nextval, :codigoDeBarras, :nombre, :costoEnBodega, :precioUnitario, :presentacion, :cantidadPresentacion, :unidadPresentacion, :especEmpaque, :fechaVencimiento, :categoria)", nativeQuery = true)
    void insertarProducto(@Param("codigoDeBarras") Integer codigoDeBarras, @Param("nombre") String nombre, @Param("costoEnBodega") Integer costoEnBodega, @Param("precioUnitario") Integer precioUnitario, @Param("presentacion") String presentacion, @Param("cantidadPresentacion") Integer cantidadPresentacion, @Param("unidadPresentacion") String unidadPresentacion, @Param("especEmpaque") String especEmpaque, @Param("fechaVencimiento") Date fechaVencimiento, @Param("categoria") Integer categoria);

    @Modifying
    @Transactional
    @Query(value = "UPDATE productos SET codigoDeBarras= :codigoDeBarras, nombre= :nombre, costoEnBodega= :costoEnBodega, precioUnitario= :precioUnitario, presentacion= :presentacion, cantidadPresentacion= :cantidadPresentacion, unidadPresentacion= :unidadPresentacion, especEmpaque= :especEmpaque, fechaVencimiento= :fechaVencimiento, categoria= :categoria WHERE id =:id", nativeQuery = true)
    void actualizarProducto(@Param("id") Integer id, @Param("codigoDeBarras") Integer codigoDeBarras, @Param("nombre") String nombre, @Param("costoEnBodega") Integer costoEnBodega, @Param("precioUnitario") Integer precioUnitario, @Param("presentacion") String presentacion, @Param("cantidadPresentacion") Integer cantidadPresentacion, @Param("unidadPresentacion") String unidadPresentacion, @Param("especEmpaque") String especEmpaque, @Param("fechaVencimiento") Date fechaVencimiento, @Param("categoria") Integer categoria);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM productos WHERE id = :id", nativeQuery = true)
    void eliminarProducto(@Param("id") Integer id);
}
