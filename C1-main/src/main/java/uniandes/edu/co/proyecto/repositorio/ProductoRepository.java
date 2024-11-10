package uniandes.edu.co.proyecto.repositorio;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;


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

    // RFC5: Listar productos que requieren orden de compra debido a nivel bajo en inventario
    @Query(value = "SELECT p.nombre, p.id, b.nombre as bodega_nombre, s.nombre as sucursal_nombre, pr.nombre as proveedor_nombre, i.cantidad " +
                   "FROM inventarios i " +
                   "JOIN productos p ON i.id_producto = p.id " +
                   "JOIN bodegas b ON i.id_bodega = b.id " +
                   "JOIN sucursales s ON b.sucursal = s.id " +
                   "LEFT JOIN ordenesDeCompra oc ON oc.producto = p.id " +
                   "LEFT JOIN proveedores pr ON oc.proveedor = pr.id " +
                   "WHERE i.cantidad < i.numero_reorden", nativeQuery = true)
    List<Object[]> findProductosConNivelBajo();

    // RFC6: Consultar documentos de ingreso a bodega con transacción SERIALIZABLE y rollback en caso de error
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Query(value = "SELECT s.nombre AS sucursal_nombre, b.nombre AS bodega_nombre, d.numero_documento, d.fecha, p.nombre AS proveedor_nombre " +
                   "FROM documentos_ingreso d " +
                   "JOIN bodegas b ON d.id_bodega = b.id " +
                   "JOIN sucursales s ON b.sucursal = s.id " +
                   "JOIN proveedores p ON d.proveedor = p.id " +
                   "WHERE d.fecha >= SYSDATE - 30 AND s.id = :sucursalId AND b.id = :bodegaId",
           nativeQuery = true)
    List<Object[]> findDocumentosIngresoRecientes(@Param("sucursalId") Long sucursalId,
                                                  @Param("bodegaId") Long bodegaId);
}
