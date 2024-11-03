package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;

import uniandes.edu.co.proyecto.modelo.Inventario;
import uniandes.edu.co.proyecto.modelo.InventarioPK;

import java.util.List;

public interface InventarioRepository extends JpaRepository<Inventario, InventarioPK> {

    // Consultar el inventario de productos en una bodega específica
    @Query("SELECT i FROM Inventario i WHERE i.pk.id_bodega.id = :idBodega")
    List<Inventario> findByBodegaId(@Param("idBodega") Integer idBodega);

    // Transacción para registrar el ingreso de productos a la bodega (actualiza inventario y costo promedio)
    @Transactional
    @Modifying
    @Query("UPDATE Inventario i SET i.cantidad = i.cantidad + :cantidad, " +
           "i.costoPromedio = ((i.costoPromedio * i.cantidad + :nuevoCosto * :cantidad) / (i.cantidad + :cantidad)) " +
           "WHERE i.pk.id_producto.id = :idProducto AND i.pk.id_bodega.id = :idBodega")
    void actualizarInventario(
        @Param("idProducto") Integer idProducto,
        @Param("idBodega") Integer idBodega,
        @Param("cantidad") Integer cantidad,
        @Param("nuevoCosto") Double nuevoCosto
    );
}
