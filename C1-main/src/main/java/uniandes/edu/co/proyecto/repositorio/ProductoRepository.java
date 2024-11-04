package uniandes.edu.co.proyecto.repositorio;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Obtener todos los productos
    @Query(value = "SELECT * FROM productos", nativeQuery = true)
    Collection<Producto> darProductos();

    // Obtener un producto por ID
    @Query(value = "SELECT * FROM productos WHERE id = :id", nativeQuery = true)
    Producto darProducto(@Param("id") Long id);

    // Insertar un nuevo producto
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO productos (id, codigoDeBarras, nombre, costoEnBodega, precioUnitario, presentacion, cantidadPresentacion, unidadPresentacion, especEmpaque, fechaVencimiento, categoria) VALUES (productos_SEQ.nextval, :codigoDeBarras, :nombre, :costoEnBodega, :precioUnitario, :presentacion, :cantidadPresentacion, :unidadPresentacion, :especEmpaque, :fechaVencimiento, :categoriaId)", nativeQuery = true)
    void insertarProducto(@Param("codigoDeBarras") Integer codigoDeBarras,
                          @Param("nombre") String nombre,
                          @Param("costoEnBodega") Integer costoEnBodega,
                          @Param("precioUnitario") Integer precioUnitario,
                          @Param("presentacion") String presentacion,
                          @Param("cantidadPresentacion") Integer cantidadPresentacion,
                          @Param("unidadPresentacion") String unidadPresentacion,
                          @Param("especEmpaque") String especEmpaque,
                          @Param("fechaVencimiento") Date fechaVencimiento,
                          @Param("categoriaId") Integer categoriaId);

    // Actualizar un producto existente
    @Modifying
    @Transactional
    @Query(value = "UPDATE productos SET codigoDeBarras = :codigoDeBarras, nombre = :nombre, costoEnBodega = :costoEnBodega, precioUnitario = :precioUnitario, presentacion = :presentacion, cantidadPresentacion = :cantidadPresentacion, unidadPresentacion = :unidadPresentacion, especEmpaque = :especEmpaque, fechaVencimiento = :fechaVencimiento, categoria = :categoriaId WHERE id = :id", nativeQuery = true)
    void actualizarProducto(@Param("id") Long id,
                            @Param("codigoDeBarras") Integer codigoDeBarras,
                            @Param("nombre") String nombre,
                            @Param("costoEnBodega") Integer costoEnBodega,
                            @Param("precioUnitario") Integer precioUnitario,
                            @Param("presentacion") String presentacion,
                            @Param("cantidadPresentacion") Integer cantidadPresentacion,
                            @Param("unidadPresentacion") String unidadPresentacion,
                            @Param("especEmpaque") String especEmpaque,
                            @Param("fechaVencimiento") Date fechaVencimiento,
                            @Param("categoriaId") Integer categoriaId);

    // Eliminar un producto por ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM productos WHERE id = :id", nativeQuery = true)
    void eliminarProducto(@Param("id") Long id);

    // Buscar productos por características específicas (RFC2)
    @Query(value = "SELECT p.* FROM productos p " +
                   "JOIN productos_bodegas pb ON p.id = pb.producto_id " +
                   "JOIN bodegas b ON pb.bodega_id = b.id " +
                   "WHERE (:categoriaId IS NULL OR p.categoria = :categoriaId) " +
                   "AND (:precioMin IS NULL OR p.precioUnitario >= :precioMin) " +
                   "AND (:precioMax IS NULL OR p.precioUnitario <= :precioMax) " +
                   "AND (:fechaVencimiento IS NULL OR p.fechaVencimiento >= :fechaVencimiento) " +
                   "AND (:sucursalId IS NULL OR b.sucursal = :sucursalId)", nativeQuery = true)
    Collection<Producto> findProductosPorCaracteristicas(@Param("categoriaId") Integer categoriaId,
                                                         @Param("precioMin") Integer precioMin,
                                                         @Param("precioMax") Integer precioMax,
                                                         @Param("fechaVencimiento") Date fechaVencimiento,
                                                         @Param("sucursalId") Integer sucursalId);
}
