package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;

import uniandes.edu.co.proyecto.modelo.Inventario;
import uniandes.edu.co.proyecto.modelo.InventarioPK;
import uniandes.edu.co.proyecto.modelo.Sucursal;

import java.util.List;

public interface InventarioRepository extends JpaRepository<Inventario, InventarioPK> {

    // Consultar el inventario de productos en una bodega específica
    @Query("SELECT i FROM Inventario i WHERE i.pk.id_bodega.id = :idBodega")
    List<Inventario> findByBodegaId(@Param("idBodega") Integer idBodega);

    // Transacción para registrar el ingreso de productos a la bodega (actualiza inventario y costo promedio)
    @Transactional
    @Modifying
    @Query("UPDATE Inventario i SET i.costoPromedio = ((i.costoPromedio * i.capacidad + :nuevoCosto * :cantidad) / (i.capacidad + :cantidad)), " +
           "i.capacidad = i.capacidad + :cantidad " +
           "WHERE i.pk.id_producto.id = :idProducto AND i.pk.id_bodega.id = :idBodega")
    void actualizarInventario(
        @Param("idProducto") Integer idProducto,
        @Param("idBodega") Integer idBodega,
        @Param("cantidad") Integer cantidad,
        @Param("nuevoCosto") Double nuevoCosto
    );

    // Consulta para el RFC3: inventario de productos en una bodega específica
    @Query("SELECT i FROM Inventario i WHERE i.pk.id_bodega.id = :idBodega AND i.pk.id_bodega.sucursal.id = :idSucursal")
    List<Inventario> findInventarioPorSucursalYBodega(
        @Param("idSucursal") Integer idSucursal,
        @Param("idBodega") Integer idBodega
    );

    // Consulta para RFC4: Obtener sucursales con disponibilidad de un producto por id o nombre
    @Query("SELECT DISTINCT s FROM Inventario i " +
           "JOIN i.pk.id_bodega b " +
           "JOIN b.sucursal s " +
           "WHERE (i.pk.id_producto.id = :idProducto OR i.pk.id_producto.nombre = :nombreProducto) " +
           "AND i.capacidad > 0")
    List<Sucursal> findSucursalesConDisponibilidadDeProducto(
        @Param("idProducto") Integer idProducto,
        @Param("nombreProducto") String nombreProducto
    );
} 